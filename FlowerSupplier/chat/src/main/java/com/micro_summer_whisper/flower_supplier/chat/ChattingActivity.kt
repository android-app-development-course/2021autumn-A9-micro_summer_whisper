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
import java.lang.Exception

import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.contentValuesOf
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.MyDatabaseHelper
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.ChatMessageVo
import com.micro_summer_whisper.flower_supplier.common.pojo.ChattingMsg
import com.micro_summer_whisper.flower_supplier.common.util.DateTimeUtils
import com.micro_summer_whisper.flower_supplier.common.util.PictureUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChattingActivity : BaseActivity() {

    private lateinit var binding: ActivityChattingBinding
    private val chattingOptionNavigation = ChattingOptionNavigationFragment.newInstance()
    private var isShowingChattingOptionNavigation = false
    private val chattingMsgList = ArrayList<ChatMessageVo>()
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

    @RequiresApi(Build.VERSION_CODES.O)
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
            val apiService = ServiceCreator.create(ApiService::class.java)
            apiService.sendChattingMsg(ChatMessageVo("https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",binding.chattingInput.text.toString(),
                ChatMessageVo.TYPE_RECEIVE_TEXT,bid.toInt(),FlowerSupplierApplication.userAccount.userId,FlowerSupplierApplication.userInfo.name,1)
            ).enqueue(object : Callback<ApiResponse<Any>>{
                override fun onResponse(
                    call: Call<ApiResponse<Any>>,
                    response: Response<ApiResponse<Any>>
                ) {
                    val apiResponse = response.body() as ApiResponse<Any>
                    if (apiResponse.success){
                        chattingMsgList.add( ChatMessageVo("https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",binding.chattingInput.text.toString(),
                            ChatMessageVo.TYPE_SEND_TEXT,FlowerSupplierApplication.userAccount.userId,bid.toInt(),FlowerSupplierApplication.userInfo.name,1))
                        adapter.notifyItemInserted(chattingMsgList.size-1)
                        binding.chattingRecyclerView.smoothScrollToPosition(chattingMsgList.size-1)
                        db.insert("chats",null, contentValuesOf(
                            "a_id" to FlowerSupplierApplication.userAccount.userId,"b_id" to bid, "content" to binding.chattingInput.text.toString(),
                            "type" to ChatMessageVo.TYPE_SEND_TEXT, "created_time" to DateTimeUtils.toStrFromLong(System.currentTimeMillis()),
                            "head_image_link" to "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg"
                            , "nick_name" to FlowerSupplierApplication.userInfo.name, "is_text" to 1)
                        )
                    } else {
                        Log.e(javaClass.simpleName,apiResponse.message)
                    }
                }

                override fun onFailure(call: Call<ApiResponse<Any>>, t: Throwable) {
                    Log.e(javaClass.simpleName,"发送聊天信息失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                }

            })

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
                            val apiService = ServiceCreator.create(ApiService::class.java)
                            apiService.sendChattingMsg(ChatMessageVo("https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
                                PictureUtils.bitmap2String(bitmap,100),
                                ChatMessageVo.TYPE_RECEIVE_PICTURE,bid.toInt(),FlowerSupplierApplication.userAccount.userId,FlowerSupplierApplication.userInfo.name,0)
                            ).enqueue(object : Callback<ApiResponse<Any>>{
                                override fun onResponse(
                                    call: Call<ApiResponse<Any>>,
                                    response: Response<ApiResponse<Any>>
                                ) {
                                    val apiResponse = response.body() as ApiResponse<Any>
                                    if (apiResponse.success){
                                        chattingMsgList.add(
                                            ChatMessageVo("https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
                                                PictureUtils.bitmap2String(bitmap,100),
                                                ChatMessageVo.TYPE_SEND_PICTURE,FlowerSupplierApplication.userAccount.userId,bid.toInt(),FlowerSupplierApplication.userInfo.name,0)
                                        )
                                        adapter.notifyItemInserted(chattingMsgList.size-1)
                                        binding.chattingRecyclerView.smoothScrollToPosition(chattingMsgList.size-1)
                                        db.insert("chats",null, contentValuesOf(
                                            "a_id" to FlowerSupplierApplication.userAccount.userId,"b_id" to bid, "content" to PictureUtils.bitmap2String(bitmap,100),
                                            "type" to ChatMessageVo.TYPE_SEND_PICTURE, "created_time" to DateTimeUtils.toStrFromLong(System.currentTimeMillis()),
                                            "head_image_link" to "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg",
                                            "nick_name" to FlowerSupplierApplication.userInfo.name, "is_text" to 0)
                                        )
                                    } else {
                                        Log.e(javaClass.simpleName,apiResponse.message)
                                    }
                                }

                                override fun onFailure(call: Call<ApiResponse<Any>>, t: Throwable) {
                                    Log.e(javaClass.simpleName,"发送图片信息失败")
                                    Log.e(javaClass.simpleName,t.stackTraceToString())
                                }

                            })


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

        chattingMsgList.clear()
        Log.d(javaClass.simpleName,"bid: $bid")
        db.let {
            it.query("chats",null,"b_id = ?", arrayOf("$bid"),null,null,"created_time").let {
                while (it.moveToNext()){
                    chattingMsgList.add(ChatMessageVo(it.getString(it.getColumnIndexOrThrow("head_image_link")),
                    it.getString(it.getColumnIndexOrThrow("content")),it.getInt(it.getColumnIndexOrThrow("type")),
                        it.getLong(it.getColumnIndexOrThrow("a_id")).toInt(),it.getLong(it.getColumnIndexOrThrow("b_id")).toInt(),
                        it.getString(it.getColumnIndexOrThrow("nick_name")),it.getInt(it.getColumnIndexOrThrow("is_text"))
                        ))
                }
            }
        }
    }



}