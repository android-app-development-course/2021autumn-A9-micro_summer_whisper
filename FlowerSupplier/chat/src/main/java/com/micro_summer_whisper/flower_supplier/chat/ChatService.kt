package com.micro_summer_whisper.flower_supplier.chat

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock

import android.app.AlarmManager

import android.os.Build

import android.content.BroadcastReceiver

import android.app.PendingIntent
import android.content.Context

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.contentValuesOf
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.DataHelper
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.MyDatabaseHelper
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.ChatMessageVo
import com.micro_summer_whisper.flower_supplier.common.pojo.ChattingMsg
import com.micro_summer_whisper.flower_supplier.common.pojo.ProductVo
import com.micro_summer_whisper.flower_supplier.common.util.DateTimeUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.concurrent.thread
import kotlin.random.Random


class ChatService : Service() {
    var TIME_INTERVAL = 5000 // 这是5s

    var pendingIntent: PendingIntent? = null
    var alarmManager: AlarmManager? = null

    override fun onCreate() {
        super.onCreate()
        val intentFilter = IntentFilter(TEST_ACTION)
        registerReceiver(receiver, intentFilter)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent()
        intent.action = TEST_ACTION
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //6.0低电量模式需要使用该方法触发定时任务
            alarmManager!!.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                pendingIntent
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //4.4以上 需要使用该方法精确执行时间
            alarmManager!!.setExact(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                pendingIntent
            )
        } else { //4。4一下 使用老方法
            alarmManager!!.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                TIME_INTERVAL.toLong(),
                pendingIntent
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    val TEST_ACTION = "com.flower_supplier.chat" + "_TEST_ACTION"

    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            if (TEST_ACTION == action) {
                println("我爱你中国")
                val apiService = ServiceCreator.create(ApiService::class.java)
                apiService.getChattingMsg(FlowerSupplierApplication.userInfo.userId).enqueue(object : Callback<ApiResponse<ArrayList<ChatMessageVo>>>{
                    override fun onResponse(
                        call: Call<ApiResponse<ArrayList<ChatMessageVo>>>,
                        response: Response<ApiResponse<ArrayList<ChatMessageVo>>>
                    ) {
                        val apiResponse = response.body() as ApiResponse<ArrayList<ChatMessageVo>>
                        if (apiResponse.success){
                            apiResponse.data?.let {
                                val chattingMsgList = it
                                DataHelper.getInstance().saveData("chattingMsgList",chattingMsgList)
                                val intent = Intent(ChatFragment.UPDATE_CHAT_MSG_ACTION)
                                sendBroadcast(intent)

                                val db = MyDatabaseHelper(FlowerSupplierApplication.context,"flower_supplier",1).writableDatabase
                                db.let {
                                    for (chattingMsg in chattingMsgList) {
                                        it.delete("latest_chats","b_id = ?", arrayOf("${chattingMsg.guestId}"))
                                        it.insert("latest_chats",null, contentValuesOf(
                                            "a_id" to chattingMsg.hostId,"b_id" to chattingMsg.guestId, "content" to chattingMsg.content,
                                            "type" to if (chattingMsg.isText==1) {ChattingMsg.TYPE_RECEIVE_TEXT} else {ChattingMsg.TYPE_RECEIVE_PICTURE}, "created_time" to DateTimeUtils.toStrFromLong(System.currentTimeMillis()),
                                            "head_image_link" to chattingMsg.headImageLink, "nick_name" to chattingMsg.nickName, "is_text" to 1))
                                        it.insert("chats",null, contentValuesOf(
                                            "a_id" to chattingMsg.hostId,"b_id" to chattingMsg.guestId, "content" to chattingMsg.content,
                                            "type" to if (chattingMsg.isText==1) {ChattingMsg.TYPE_RECEIVE_TEXT} else {ChattingMsg.TYPE_RECEIVE_PICTURE}, "created_time" to DateTimeUtils.toStrFromLong(System.currentTimeMillis()),
                                            "head_image_link" to chattingMsg.headImageLink, "is_text" to 0, "nick_name" to chattingMsg.nickName))
                                    }
                                }
                            }

                        } else {
                            Log.e(javaClass.simpleName,apiResponse.message)
                        }
                    }
                    override fun onFailure(call: Call<ApiResponse<ArrayList<ChatMessageVo>>>, t: Throwable) {
                            Log.e(javaClass.simpleName,"获取聊天信息失败")
                            Log.e(javaClass.simpleName,t.stackTraceToString())

                    }
                })
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager!!.setExactAndAllowWhileIdle(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + TIME_INTERVAL,
                        pendingIntent
                    )
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmManager!!.setExact(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + TIME_INTERVAL,
                        pendingIntent
                    )
                }
            }
        }
    }

}