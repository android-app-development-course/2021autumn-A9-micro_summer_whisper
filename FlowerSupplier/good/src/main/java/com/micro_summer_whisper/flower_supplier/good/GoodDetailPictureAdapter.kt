package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
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

class GoodDetailPictureAdapter(val context: Context, val picLinkList:ArrayList<String>): RecyclerView.Adapter<GoodDetailPictureAdapter.GoodDetailPictureViewHolder>() {

    inner class GoodDetailPictureViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.good_detail_showpicture_item_image)
        val closeImageView: ImageView = itemView.findViewById(R.id.good_detail_showpicture_item_close)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodDetailPictureViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.good_detail_showpicture_item, parent,false)
        return GoodDetailPictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoodDetailPictureViewHolder, position: Int) {
        val pic = picLinkList[position]
        if ("".equals(pic)){
            holder.imageView.setImageResource(R.drawable.add)
        } else {
            Glide.with(context).load(Uri.parse(pic)).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return picLinkList.size
    }


}

