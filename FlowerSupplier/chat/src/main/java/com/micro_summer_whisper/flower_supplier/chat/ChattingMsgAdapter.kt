package com.micro_summer_whisper.flower_supplier.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

class ChattingMsgAdapter(val msgList:ArrayList<ChattingMsg>): RecyclerView.Adapter<ChattingMsgViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val msg:ChattingMsg = msgList[position]
        return msg.msgType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingMsgViewHolder {
        val view:View
        if (viewType==ChattingMsg.TYPE_SEND){
            view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_right_msg_item,parent,false)
            return ChattingRightViewHolder(view)
        } else if (viewType==ChattingMsg.TYPE_RECEIVE) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_left_msg_item,parent,false)
            return ChattingLeftViewHolder(view)
        } else {
            throw RuntimeException("onCreateViewHolder unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: ChattingMsgViewHolder, position: Int) {
        val msg:ChattingMsg = msgList[position]
        when(holder){
            is ChattingLeftViewHolder -> {
                holder.headImage.setImageResource(msg.imageId)
                holder.leftMsg.setText(msg.content)
            }
            is ChattingRightViewHolder -> {
                holder.headImage.setImageResource(msg.imageId)
                holder.rightMsg.setText(msg.content)
            }
        }

    }
    override fun getItemCount(): Int {
        return msgList.size
    }
}

open class ChattingMsgViewHolder (view: View): RecyclerView.ViewHolder(view) {

}

class ChattingLeftViewHolder(view: View):ChattingMsgViewHolder(view){
    val headImage: ImageView = view.findViewById(R.id.chatting_left_image)
    val leftMsg: TextView = view.findViewById(R.id.chatting_left_text)
}

class ChattingRightViewHolder(view: View):ChattingMsgViewHolder(view){
    val headImage: ImageView = view.findViewById(R.id.chatting_right_image)
    val rightMsg: TextView = view.findViewById(R.id.chatting_right_text)
}


