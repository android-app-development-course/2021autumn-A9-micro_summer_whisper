package com.micro_summer_whisper.flower_supplier.chat


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.chat.databinding.ActivityChattingBinding
import com.micro_summer_whisper.flower_supplier.chat.databinding.ChattingToolbarCustomViewBinding
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.randomTimes
import com.micro_summer_whisper.flower_supplier.common.shortToast
import kotlin.random.Random




class ChattingActivity : BaseActivity() {

    private lateinit var binding: ActivityChattingBinding


    private val chattingMsgList = ArrayList<ChattingMsg>()

    companion object {
        fun actionStart(context: Context){
            val intent = Intent(context,ChattingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setTitle("JOE")
        }
        initChatMsgList()
        val layoutManager = LinearLayoutManager(this)
        val adapter = ChattingMsgAdapter(chattingMsgList)
        binding.chattingRecyclerView.layoutManager = layoutManager
        binding.chattingRecyclerView.adapter = adapter
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chatting_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
//            R.id.chatting_order -> {
//                "有15个订单".shortToast()
//            }
//            R.id.chatting_person_info -> {
//                "客户信息".shortToast()
//            }
        }
        return true
    }

    private fun initChatMsgList(){
        chattingMsgList.clear()
        val random = Random(System.currentTimeMillis())
        repeat(30){
            if (random.nextInt(3)==2){
                chattingMsgList.add(ChattingMsg(R.drawable.joe, "hello ${random.nextInt()}".randomTimes(20), ChattingMsg.TYPE_RECEIVE))
            } else {
                chattingMsgList.add(ChattingMsg(R.drawable.ross, "hi ${random.nextInt()}".randomTimes(20), ChattingMsg.TYPE_SEND))
            }
        }
    }



}