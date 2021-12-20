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
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.MyDatabaseHelper
import com.micro_summer_whisper.flower_supplier.common.pojo.ChattingMsg
import java.util.*


class ChatMsgAdapter(val recyclerView: RecyclerView, val fragment: Fragment ,val chatList:LinkedList<ChattingMsg>): RecyclerView.Adapter<ChatMsgAdapter.ChatMsgViewHolder>() {

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
        Glide.with(fragment).load(data.headImageLink).into(holder.imageView)
        holder.titleTV.setText(data.nickName)
        holder.shortConTV.setText(if (data.isText){data.content.toString(Charsets.UTF_8)} else {"图片"})
        holder.itemView.setOnLongClickListener {
            fragment.context?.let {
                val dialog = AlertDialog.Builder(it)
                dialog.setItems(arrayOf("删除", "置顶"), DialogInterface.OnClickListener { dialogInterface, i ->
                    when(i){
                        0 -> {
                            val pos = holder.adapterPosition
                            val removed = chatList.removeAt(pos)
                            notifyItemRemoved(pos)
                            val db = MyDatabaseHelper(FlowerSupplierApplication.context,"flower_supplier",1).writableDatabase
                            db.let {
                                it.delete("latest_chats","b_id = ?", arrayOf("${removed.bId}"))
                                it.delete("chats","b_id = ?", arrayOf("${removed.bId}"))
                            }
                        }
                        1 -> {
                            val pos = holder.adapterPosition
                            val removeAt = chatList.removeAt(pos)
                            notifyItemRemoved(pos)
                            recyclerView.smoothScrollToPosition(0)
                            chatList.addFirst(removeAt)
                            notifyItemInserted(0)
                        }
                    }
                })
                dialog.show()
            }
            true
        }
        holder.itemView.setOnClickListener {
            fragment.activity?.let {
                val cm = chatList[holder.adapterPosition]
                ChattingActivity.actionStart(it,cm.bId,cm.nickName)
            }
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }


}

