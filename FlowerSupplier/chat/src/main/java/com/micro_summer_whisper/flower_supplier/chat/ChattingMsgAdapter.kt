package com.micro_summer_whisper.flower_supplier.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.pojo.ChattingMsg
import java.lang.RuntimeException
import java.nio.charset.Charset

class ChattingMsgAdapter(val msgList:ArrayList<ChattingMsg>): RecyclerView.Adapter<ChattingMsgViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val msg: ChattingMsg = msgList[position]
        return msg.msgType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingMsgViewHolder {
        val view:View
        when(viewType){
            ChattingMsg.TYPE_SEND_TEXT -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_right_msg_item,parent,false)
                return ChattingRightTextViewHolder(view)
            }
            ChattingMsg.TYPE_SEND_PICTURE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_right_msg_picture_item,parent,false)
                return ChattingRightPictureViewHolder(view)
            }
            ChattingMsg.TYPE_RECEIVE_TEXT -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_left_msg_item,parent,false)
                return ChattingLeftTextViewHolder(view)
            }
            ChattingMsg.TYPE_RECEIVE_PICTURE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_left_msg_picture_item,parent,false)
                return ChattingLeftPictureViewHolder(view)
            }
            else -> {
                throw RuntimeException("")
            }
        }
    }

    override fun onBindViewHolder(holder: ChattingMsgViewHolder, position: Int) {
        val msg: ChattingMsg = msgList[position]
        when(holder){
            is ChattingRightTextViewHolder -> {
                Glide.with(holder.itemView).load(msg.headImageLink).into(holder.headImage)
                holder.rightText.setText(String(msg.content, Charset.forName("UTF-8")) )
            }
            is ChattingRightPictureViewHolder -> {
                Glide.with(holder.itemView).load(msg.headImageLink).into(holder.headImage)
                Glide.with(holder.itemView).load(String(msg.content,Charset.forName("UTF-8"))).into(holder.rightPic)
            }
            is ChattingLeftTextViewHolder -> {
                Glide.with(holder.itemView).load(msg.headImageLink).into(holder.headImage)
                holder.leftText.setText(String(msg.content, Charset.forName("UTF-8")) )
            }
            is ChattingLeftPictureViewHolder -> {
                Glide.with(holder.itemView).load(msg.headImageLink).into(holder.headImage)
                Glide.with(holder.itemView).load(String(msg.content,Charset.forName("UTF-8"))).into(holder.leftPic)
            }
        }

    }
    override fun getItemCount(): Int {
        return msgList.size
    }
}

open class ChattingMsgViewHolder (view: View): RecyclerView.ViewHolder(view) {

}

open class ChattingLeftViewHolder(view: View):ChattingMsgViewHolder(view){
    val headImage: ImageView = view.findViewById(R.id.chatting_left_image)
}

class ChattingLeftTextViewHolder(view: View):ChattingLeftViewHolder(view){
    val leftText: TextView = view.findViewById(R.id.chatting_left_text)
}

class ChattingLeftPictureViewHolder(view: View):ChattingLeftViewHolder(view){
    val leftPic: ImageView = view.findViewById(R.id.chatting_left_picture)
}


open class ChattingRightViewHolder(view: View):ChattingMsgViewHolder(view){
    val headImage: ImageView = view.findViewById(R.id.chatting_right_image)
}

class ChattingRightTextViewHolder(view: View):ChattingRightViewHolder(view){
    val rightText: TextView = view.findViewById(R.id.chatting_right_text)
}

class ChattingRightPictureViewHolder(view: View):ChattingRightViewHolder(view){
    val rightPic: ImageView = view.findViewById(R.id.chatting_right_picture)
}



