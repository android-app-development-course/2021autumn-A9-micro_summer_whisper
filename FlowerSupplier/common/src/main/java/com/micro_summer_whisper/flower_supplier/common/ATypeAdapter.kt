package com.micro_summer_whisper.flower_supplier.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

open class ATypeAdapter<E>(val atypeList: List<AType<E>>, val layoutId:Int, val imageViewId: Int?, val textViewId:Int?, val btnId: Int?): RecyclerView.Adapter<ATypeAdapter<E>.ATypeViewHolder>() {
    inner class ATypeViewHolder(itemView: View, imageViewId: Int?, textViewId:Int?,btnId: Int?): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView? = imageViewId?.let { itemView.findViewById(it) }
        val textView: TextView? = textViewId?.let { itemView.findViewById(it) }
        val btn: Button? = btnId?.let { itemView.findViewById(it) }
    }

    open fun itemViewOnClickListener(viewHolder: ATypeViewHolder, context:Context): View.OnClickListener?{
        return null
    }
    open fun imageViewOnClickListener(viewHolder: ATypeViewHolder, context:Context): View.OnClickListener?{
        return null
    }
    open fun textViewOnClickListener(viewHolder: ATypeViewHolder, context:Context): View.OnClickListener?{
        return null
    }
    open fun btnOnClickListener(viewHolder: ATypeViewHolder, context:Context): View.OnClickListener?{
        return null
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ATypeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId ,parent,false)
        val viewHolder = ATypeViewHolder(view,imageViewId, textViewId,btnId)
        if (viewHolder.itemView!=null){
            val itemViewOnClickListener = itemViewOnClickListener(viewHolder, parent.context)
            itemViewOnClickListener?.let {
                viewHolder.itemView.setOnClickListener(it)
            }
        }
        if (viewHolder.imageView!=null){
            val imageViewOnClickListener = imageViewOnClickListener(viewHolder, parent.context)
            imageViewOnClickListener?.let {
                viewHolder.imageView.setOnClickListener(it)
            }
        }
        if (viewHolder.textView!=null){
            val textViewOnClickListener = textViewOnClickListener(viewHolder, parent.context)
            textViewOnClickListener?.let {
                viewHolder.textView.setOnClickListener(it)
            }
        }
        if (viewHolder.btn!=null){
            val onClickListener = btnOnClickListener(viewHolder, parent.context)
            onClickListener?.let {
                viewHolder.btn.setOnClickListener(it)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ATypeViewHolder, position: Int) {
        val atype = atypeList[position]
        if (atype.imageId!=null&&holder.imageView!=null){
            holder.imageView.setImageResource(atype.imageId)
        }
        if (atype.contentText!=null&&holder.textView!=null){
            holder.textView.text = atype.contentText
        }
        if (atype.btnText!=null&&holder.btn!=null){
            holder.btn.text = atype.btnText
        }

    }

    override fun getItemCount(): Int = atypeList.size

}