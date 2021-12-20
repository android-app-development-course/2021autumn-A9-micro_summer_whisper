package com.micro_summer_whisper.flower_supplier.good

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.pojo.Good



class GoodAdapter(val fragment: Fragment, val goodList:ArrayList<Good>): RecyclerView.Adapter<GoodAdapter.GoodViewHolder>() {

    inner class GoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.good_cover_image)
        val titleTV: TextView = itemView.findViewById(R.id.good_title)
        val priceTV: TextView = itemView.findViewById(R.id.good_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.good_item, parent,false)
        return GoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoodViewHolder, position: Int) {
        val good = goodList[position]
        Glide.with(fragment).load(good.coverUri).into(holder.imageView)
        holder.titleTV.setText(good.title)
        holder.priceTV.setText(good.price.toString())
        holder.itemView.setOnClickListener {
            fragment.context?.let {
                GoodDetailActivity.actionStart(it, true, good)
            }
        }
    }

    override fun getItemCount(): Int {
        return goodList.size
    }


}

