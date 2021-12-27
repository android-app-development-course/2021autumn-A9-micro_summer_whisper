package com.micro_summer_whisper.flower_supplier.chat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.chat.databinding.FragmentChatBinding
import com.micro_summer_whisper.flower_supplier.common.MyDatabaseHelper
import com.micro_summer_whisper.flower_supplier.common.pojo.ChattingMsg
import kotlinx.android.synthetic.main.fragment_chat.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val chatMsgList = LinkedList<ChattingMsg>()
    private val receiver = object : BroadcastReceiver(){
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onReceive(p0: Context?, p1: Intent?) {
            p1?.let {
                if (it.action==UPDATE_CHAT_MSG_ACTION){
                    val serializableExtra = it.getSerializableExtra("chattingMsgList") as ArrayList<ChattingMsg>
                    for (chattingMsg in serializableExtra) {
                        chatMsgList.removeIf { chatMsg ->
                            chatMsg.nickName.equals(chattingMsg.nickName)
                        }
                        chatMsgList.addFirst(chattingMsg)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
    private lateinit var adapter: ChatMsgAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root

        initChatMsgList()
        activity?.let {
            adapter = ChatMsgAdapter( binding.chatRecycleView ,this ,chatMsgList)
            binding.chatRecycleView.adapter = adapter
            val layoutManager = LinearLayoutManager(it)
            binding.chatRecycleView.layoutManager = layoutManager

            binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
            binding.swipeRefresh.setOnRefreshListener {
                refreshChatMsgList(adapter)
            }
            val intent = Intent(it,ChatService::class.java)
            it.startService(intent)
            val intentFilter = IntentFilter(UPDATE_CHAT_MSG_ACTION)
            it.registerReceiver(receiver,intentFilter)

        }
        return view
    }

    private fun initChatMsgList(){

//        val uriL = arrayListOf(
//            "https://cdn2.jianshu.io/assets/default_avatar/12-aeeea4bedf10f2a12c0d50d626951489.jpg",
//            "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
//            "https://cdn2.jianshu.io/assets/default_avatar/8-a356878e44b45ab268a3b0bbaaadeeb7.jpg"
//            )
//        val titL = arrayListOf("米拉~~","咕咕笼","美食大叔")
//        repeat(30){
//            val i = Random.nextInt(3)
//            chatMsgList.add(ChatMsg(uriL[i], titL[i], "hello $i".randomTimes(20)))
//        }
        chatMsgList.clear()
        val db = this.context?.let { MyDatabaseHelper(it,"flower_supplier",1).readableDatabase }
        db?.let {
            it.query(true,"latest_chats", null,null,null,null,null,null,null).let {
                while (it.moveToNext()){
                    val type = it.getInt(it.getColumnIndexOrThrow("is_text"))
                    chatMsgList.add(
                        ChattingMsg(it.getString(it.getColumnIndexOrThrow("head_image_link")),
                            it.getString(it.getColumnIndexOrThrow("content")),it.getInt(it.getColumnIndexOrThrow("type")),
                            it.getLong(it.getColumnIndexOrThrow("a_id")),it.getLong(it.getColumnIndexOrThrow("b_id")),it.getString(it.getColumnIndexOrThrow("nick_name")),it.getInt(it.getColumnIndexOrThrow("is_text"))==1
                        )
                    )
                }
            }

        }
    }

    private fun refreshChatMsgList(adapter: ChatMsgAdapter){
        activity?.let {
            thread {
                Thread.sleep(2000)
                it.runOnUiThread{
                    initChatMsgList()
                    adapter.notifyDataSetChanged()
                    swipeRefresh.isRefreshing = false
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        activity?.let {
            val intent = Intent(it,ChatService::class.java)
            it.stopService(intent)
            it.unregisterReceiver(receiver)
        }
        _binding = null
    }


    companion object {
        const val UPDATE_CHAT_MSG_ACTION = "com.flower_supplier.action.UPDATE_CHAT_MSG_ACTION"
        fun newInstance(): ChatFragment {
            return ChatFragment().apply {

            }
        }
    }
}