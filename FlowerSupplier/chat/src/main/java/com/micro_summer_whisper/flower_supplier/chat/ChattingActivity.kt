package com.micro_summer_whisper.flower_supplier.chat


import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.chat.databinding.ActivityChattingBinding
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.randomTimes
import java.lang.Exception
import kotlin.random.Random

import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.core.content.contentValuesOf
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.MyDatabaseHelper
import com.micro_summer_whisper.flower_supplier.common.pojo.ChattingMsg
import com.micro_summer_whisper.flower_supplier.common.util.DateTimeUtils


class ChattingActivity : BaseActivity() {

    private lateinit var binding: ActivityChattingBinding
    private val chattingOptionNavigation = ChattingOptionNavigationFragment.newInstance()
    private var isShowingChattingOptionNavigation = false
    private val chattingMsgList = ArrayList<ChattingMsg>()
    private lateinit var adapter: ChattingMsgAdapter
    private var bid: Long = -1
    val db = MyDatabaseHelper(FlowerSupplierApplication.context,"flower_supplier",1).writableDatabase

    companion object {
        fun actionStart(context: Context, bId: Long, nickName: String){
            val intent = Intent(context,ChattingActivity::class.java)
            intent.putExtra("bId",bId)
            intent.putExtra("nickName",nickName)
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
            it.setTitle(intent.getStringExtra("nickName"))
        }
        bid = intent.getLongExtra("bId",-1)
        initChatMsgList()
        val layoutManager = LinearLayoutManager(this)
        adapter = ChattingMsgAdapter(chattingMsgList)
        binding.chattingRecyclerView.layoutManager = layoutManager
        binding.chattingRecyclerView.adapter = adapter
        binding.chattingRecyclerView.scrollToPosition(chattingMsgList.size-1)

        val tran = supportFragmentManager.beginTransaction()
        tran.add(R.id.chatting_option_navigation,chattingOptionNavigation)
        tran.hide(chattingOptionNavigation)
        tran.commit()
        binding.chattingToOptionNavigation.setOnClickListener {
            if (isShowingChattingOptionNavigation){
                val tran = supportFragmentManager.beginTransaction()
                tran.hide(chattingOptionNavigation)
                tran.commit()
                isShowingChattingOptionNavigation = false
            } else {
                val tran = supportFragmentManager.beginTransaction()
                tran.show(chattingOptionNavigation)
                tran.commit()
                isShowingChattingOptionNavigation = true
            }
        }
        binding.chattingSendBtn.setOnClickListener {
            chattingMsgList.add(
                ChattingMsg("https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",binding.chattingInput.text.toString().toByteArray(),
                    ChattingMsg.TYPE_SEND_TEXT,FlowerSupplierApplication.userAccount.userId,bid,FlowerSupplierApplication.userInfo.nickName,true)
            )
            adapter.notifyItemInserted(chattingMsgList.size-1)
            binding.chattingRecyclerView.smoothScrollToPosition(chattingMsgList.size-1)
            db.insert("chats",null, contentValuesOf(
                "a_id" to FlowerSupplierApplication.userAccount.userId,"b_id" to bid, "content" to binding.chattingInput.text.toString(),
                "type" to ChattingMsg.TYPE_SEND_TEXT, "created_time" to DateTimeUtils.toStrFromLong(System.currentTimeMillis()),
                "head_image_link" to "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg"
                , "nick_name" to FlowerSupplierApplication.userInfo.nickName, "is_text" to 1)
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            ChattingOptionNavigationFragment.IMAGE_REQUEST_CODE -> if (resultCode === RESULT_OK) { //resultcode是setResult里面设置的code值
                try {
                    data?.let {
                        val selectedImage: Uri? = it.getData() //获取系统返回的照片的Uri
                        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                        val cursor: Cursor? = selectedImage?.let { it1 ->
                            contentResolver.query(
                                it1,
                                filePathColumn, null, null, null
                            )
                        }
                        cursor?.let {
                            it.moveToFirst()
                            val columnIndex: Int = it.getColumnIndex(filePathColumn[0])
                            val path = it.getString(columnIndex) //获取照片路径
                            it.close()
                            val bitmap = BitmapFactory.decodeFile(path)
                            chattingMsgList.add(
                                ChattingMsg("https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
                                "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg".toByteArray(),
                                    ChattingMsg.TYPE_SEND_PICTURE,FlowerSupplierApplication.userAccount.userId,bid,FlowerSupplierApplication.userInfo.nickName,false)
                            )
                            adapter.notifyItemInserted(chattingMsgList.size-1)
                            binding.chattingRecyclerView.smoothScrollToPosition(chattingMsgList.size-1)
                            db.insert("chats",null, contentValuesOf(
                                "a_id" to FlowerSupplierApplication.userAccount.userId,"b_id" to bid, "content" to "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
                                "type" to ChattingMsg.TYPE_SEND_PICTURE, "created_time" to DateTimeUtils.toStrFromLong(System.currentTimeMillis()),
                                "head_image_link" to "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
                                "nick_name" to FlowerSupplierApplication.userInfo.nickName, "is_text" to 1)
                            )
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
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

//        val random = Random(System.currentTimeMillis())
//        repeat(30){
//            if (random.nextInt(3)==2){
//                chattingMsgList.add(
//                    ChattingMsg("https://cdn2.jianshu.io/assets/default_avatar/12-aeeea4bedf10f2a12c0d50d626951489.jpg",
//                    "hi ${random.nextInt()}".randomTimes(20).toByteArray(), ChattingMsg.TYPE_RECEIVE_TEXT)
//                )
//                chattingMsgList.add(
//                    ChattingMsg("https://cdn2.jianshu.io/assets/default_avatar/12-aeeea4bedf10f2a12c0d50d626951489.jpg",
//                    "https://cdn2.jianshu.io/assets/default_avatar/12-aeeea4bedf10f2a12c0d50d626951489.jpg".toByteArray(), ChattingMsg.TYPE_RECEIVE_PICTURE)
//                )
//            } else {
//                chattingMsgList.add(
//                    ChattingMsg("https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
//                    "hi ${random.nextInt()}".randomTimes(20).toByteArray(), ChattingMsg.TYPE_SEND_TEXT)
//                )
//                chattingMsgList.add(
//                    ChattingMsg("https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
//                    "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg".toByteArray(), ChattingMsg.TYPE_SEND_PICTURE)
//                )
//            }
//        }
        chattingMsgList.clear()
        Log.d(javaClass.simpleName,"bid: $bid")
        db.let {
            it.query("chats",null,"b_id = ?", arrayOf("$bid"),null,null,"created_time").let {
                while (it.moveToNext()){
                    chattingMsgList.add(ChattingMsg(it.getString(it.getColumnIndexOrThrow("head_image_link")),
                    it.getString(it.getColumnIndexOrThrow("content")).toByteArray(),it.getInt(it.getColumnIndexOrThrow("type")),
                        it.getLong(it.getColumnIndexOrThrow("a_id")),it.getLong(it.getColumnIndexOrThrow("b_id")),it.getString(it.getColumnIndexOrThrow("nick_name")),it.getInt(it.getColumnIndexOrThrow("is_text"))==1
                        ))
                }
            }
        }
    }



}