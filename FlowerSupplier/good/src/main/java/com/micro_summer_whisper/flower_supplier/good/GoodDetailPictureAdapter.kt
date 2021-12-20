package com.micro_summer_whisper.flower_supplier.good

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GoodDetailPictureAdapter(val context: Context, val picBitmapList:ArrayList<Bitmap>): RecyclerView.Adapter<GoodDetailPictureAdapter.GoodDetailPictureViewHolder>() {

    inner class GoodDetailPictureViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.good_detail_showpicture_item_image)
        val closeImageView: ImageView = itemView.findViewById(R.id.good_detail_showpicture_item_close)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodDetailPictureViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.good_detail_showpicture_item, parent,false)
        return GoodDetailPictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoodDetailPictureViewHolder, position: Int) {
        val pic = picBitmapList[position]
        Glide.with(context).load(pic).into(holder.imageView)
        holder.closeImageView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            val c = context as Activity
            c.let {
                it.startActivityForResult(intent, GoodDetailActivity.SHOW_PICTURE_REQUEST_CODE*10+position)
            }
        }
    }

    override fun getItemCount(): Int {
        return picBitmapList.size
    }


}

