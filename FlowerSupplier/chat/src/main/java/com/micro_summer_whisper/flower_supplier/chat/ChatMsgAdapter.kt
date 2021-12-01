package com.micro_summer_whisper.flower_supplier.chat


import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.shortToast



class ChatMsgAdapter(val fragment: Fragment ,val chatList:ArrayList<ChatMsg>): RecyclerView.Adapter<ChatMsgAdapter.ChatMsgViewHolder>() {

    inner class ChatMsgViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.chat_msg_head_image)
        val titleTV: TextView = itemView.findViewById(R.id.chat_msg_title)
        val shortConTV: TextView = itemView.findViewById(R.id.chat_msg_short_content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMsgViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.chat_msg_item, parent,false)
        return ChatMsgViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatMsgViewHolder, position: Int) {
        val data = chatList[position]
        Glide.with(fragment).load(data.imageUri).into(holder.imageView)
        holder.titleTV.setText(data.title)
        holder.shortConTV.setText(data.shortContent)
        holder.itemView.setOnLongClickListener {
            fragment.context?.let {
                val dialog = AlertDialog.Builder(it)
                dialog.setItems(arrayOf("删除", "置顶"), DialogInterface.OnClickListener { dialogInterface, i ->
                    when(i){
                        0 -> {
                            val pos = holder.adapterPosition
                            chatList.removeAt(pos)
                            notifyItemRemoved(pos)
                        }
                        1 -> {
                            "点击置顶".shortToast()
                        }
                    }
                })
                dialog.show()
            }
            true
        }
        holder.itemView.setOnClickListener {
            fragment.activity?.let {
                ChattingActivity.actionStart(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }


}

