package com.micro_summer_whisper.flower_supplier.good

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.micro_summer_whisper.flower_supplier.common.pojo.CategoryVo


abstract class GoodCategoryAdapter(val fragment: Fragment, val goodCategoryList:ArrayList<CategoryVo>): RecyclerView.Adapter<GoodCategoryAdapter.GoodCategoryViewHolder>() {

    inner class GoodCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.good_category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodCategoryViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.good_category_item, parent,false)
        return GoodCategoryViewHolder(view)
    }

    abstract fun nameOnClickListener(holder: GoodCategoryViewHolder): View.OnClickListener

    override fun onBindViewHolder(holder: GoodCategoryViewHolder, position: Int) {
        val category = goodCategoryList[position]
        holder.nameTV.setText(category.categoryName)
        holder.nameTV.setOnClickListener(nameOnClickListener(holder))
    }

    override fun getItemCount(): Int {
        return goodCategoryList.size
    }


}

