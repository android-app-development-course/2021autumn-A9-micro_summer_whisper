package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.micro_summer_whisper.flower_supplier.common.pojo.GoodDetailNormalItem

class GoodDetailNormalAdapter(val context: Context, val goodDetailList:ArrayList<GoodDetailNormalItem>): RecyclerView.Adapter<GoodDetailNormalAdapter.GoodDetailNormalViewHolder>() {

    inner class GoodDetailNormalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.good_detail_normal_text1)
        val input: EditText = itemView.findViewById(R.id.good_detail_normal_text2)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodDetailNormalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.good_detail_normal_item, parent,false)
        return GoodDetailNormalViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoodDetailNormalViewHolder, position: Int) {
        val goodDetail = goodDetailList[position]
        holder.title.setText(goodDetail.title)
        holder.input.setText(goodDetail.content)
        holder.input.filters = arrayOf(InputFilter.LengthFilter(goodDetail.maxLength))
        holder.input.hint = "最多${goodDetail.maxLength}个字符"
        holder.input.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                goodDetail.content = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }


    override fun getItemCount(): Int {
        return goodDetailList.size
    }


}

