package com.micro_summer_whisper.flower_supplier.chat

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.chat.databinding.FragmentChatBinding
import com.micro_summer_whisper.flower_supplier.common.randomTimes
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlin.concurrent.thread
import kotlin.random.Random


class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val chatMsgList = ArrayList<ChatMsg>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root

        initChatMsgList()
        activity?.let {
            val adapter = ChatMsgAdapter(this ,chatMsgList)
            binding.chatRecycleView.adapter = adapter
            val layoutManager = LinearLayoutManager(it)
            binding.chatRecycleView.layoutManager = layoutManager

            binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
            binding.swipeRefresh.setOnRefreshListener {
                refreshChatMsgList(adapter)
            }

        }

        return view
    }

    private fun initChatMsgList(){
        chatMsgList.clear()
        val uriL = arrayListOf(
            "https://cdn2.jianshu.io/assets/default_avatar/12-aeeea4bedf10f2a12c0d50d626951489.jpg",
            "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
            "https://cdn2.jianshu.io/assets/default_avatar/8-a356878e44b45ab268a3b0bbaaadeeb7.jpg"
            )
        val titL = arrayListOf("米拉~~","咕咕笼","美食大叔")
        repeat(30){
            val i = Random.nextInt(3)
            chatMsgList.add(ChatMsg(Uri.parse(uriL[i]), titL[i], "hello $i".randomTimes(20)))
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
        _binding = null
    }


    companion object {
        fun newInstance(): ChatFragment {
            return ChatFragment().apply {

            }
        }
    }
}