package com.micro_summer_whisper.flower_supplier.store

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.lang.RuntimeException
import android.widget.Toast

import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore

import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.micro_summer_whisper.flower_supplier.common.shortToast


class StoreSettingAdapter(val context: Context ,val storeSettingItemList:ArrayList<StoreSettingItem>): RecyclerView.Adapter<StoreSettingViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val storeSettingItem = storeSettingItemList[position]
        return storeSettingItem.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreSettingViewHolder {
        val view:View
        if (viewType==StoreSettingItem.TEXT){
            view = LayoutInflater.from(context).inflate(R.layout.store_setting_item_text,parent,false)
            return StoreSettingTextViewHolder(view)
        } else if (viewType==StoreSettingItem.IMAGE) {
            view = LayoutInflater.from(context).inflate(R.layout.store_setting_item_image,parent,false)
            return StoreSettingImageViewHolder(view)
        } else {
            throw RuntimeException("onCreateViewHolder unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: StoreSettingViewHolder, position: Int) {
        val storeSettingItem = storeSettingItemList[position]
        holder.leftText.setText(storeSettingItem.leftText)
        holder.image1.setImageResource(R.drawable.right)
        when(holder){
            is StoreSettingTextViewHolder -> {
                holder.rightText.setText(storeSettingItem.rightText)
                holder.image1.setOnClickListener {
                    val editText = EditText(context)
                    val inputDialog = AlertDialog.Builder(context)
                    inputDialog.setTitle("输入新的内容").setView(editText)
                    inputDialog.setPositiveButton("保存",
                        DialogInterface.OnClickListener { dialog, which ->
                            val pos = holder.adapterPosition
                            storeSettingItemList[pos].rightText = editText.text.toString()
                            this.notifyItemChanged(pos)
                        }).show()
                }
            }
            is StoreSettingImageViewHolder -> {
                Glide.with(context).load(storeSettingItem.imageLink).into(holder.image2)
                holder.image1.setOnClickListener {
                    val intent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    context.let {
                        val c = context as Activity
                        c.startActivityForResult(intent, StoreSettingActivity.IMAGE_REQUEST_CODE)
                    }
                }
            }
        }

    }





    override fun getItemCount(): Int {
        return storeSettingItemList.size
    }

}

open class StoreSettingViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val leftText: TextView = view.findViewById(R.id.store_setting_item_text1)
    val image1: ImageView = view.findViewById(R.id.store_setting_item_image1)
}

class StoreSettingTextViewHolder(view: View):StoreSettingViewHolder(view){
    val rightText: TextView = view.findViewById(R.id.store_setting_item_text2)
}

class StoreSettingImageViewHolder(view: View):StoreSettingViewHolder(view){
    val image2: ImageView = view.findViewById(R.id.store_setting_item_image2)
}


