package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.Good

class GoodDetailNormalAdapter(val context: Context, val goodDetailList:ArrayList<Pair<String,String>>): RecyclerView.Adapter<GoodDetailNormalAdapter.GoodDetailNormalViewHolder>() {

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
        holder.title.setText(goodDetail.first)
        holder.input.setText(goodDetail.second)
    }

    override fun getItemCount(): Int {
        return goodDetailList.size
    }


}

