package com.micro_summer_whisper.flower_supplier.common

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity","${javaClass.simpleName} oncreate")
        supportActionBar?.hide()
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
        Log.d("BaseActivity","${javaClass.simpleName} ondestroy")
    }


    override fun onResume() {
        super.onResume()
        Log.d("BaseActivity","${javaClass.simpleName} onresume")
    }


}