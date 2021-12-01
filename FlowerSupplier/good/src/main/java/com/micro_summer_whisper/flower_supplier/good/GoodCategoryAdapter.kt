package com.micro_summer_whisper.flower_supplier.good

import android.content.DialogInterface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.Good



abstract class GoodCategoryAdapter(val fragment: Fragment, val goodCategoryList:ArrayList<String>): RecyclerView.Adapter<GoodCategoryAdapter.GoodCategoryViewHolder>() {

    inner class GoodCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.good_category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodCategoryViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.good_category_item, parent,false)
        return GoodCategoryViewHolder(view)
    }

    abstract fun nameOnClickListener(holder: GoodCategoryViewHolder): View.OnClickListener

    override fun onBindViewHolder(holder: GoodCategoryViewHolder, position: Int) {
        val categoryName = goodCategoryList[position]
        holder.nameTV.setText(categoryName)
        holder.nameTV.setOnClickListener(nameOnClickListener(holder))
    }

    override fun getItemCount(): Int {
        return goodCategoryList.size
    }


}

