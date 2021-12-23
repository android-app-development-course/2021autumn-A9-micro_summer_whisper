package com.micro_summer_whisper.flower_supplier.common

import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.launcher.ARouter
import com.micro_summer_whisper.flower_supplier.common.pojo.Account
import com.micro_summer_whisper.flower_supplier.common.pojo.Person
import com.micro_summer_whisper.flower_supplier.common.pojo.Shop
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import java.time.LocalDateTime


class FlowerSupplierApplication: Application() {

    companion object {
        lateinit var context: Context
        val ignoreNetworkFail = false //true，忽略网络请求失败；false，正常
        val isDebug = true
        val isModuleDevelop= true
        var isLogin = false
        lateinit var userAccount: Account
        lateinit var userInfo: Person
        lateinit var store: Shop
        lateinit var TOKEN: String
        fun getBitmapFormUri(imageUri: Uri) = context.contentResolver.openFileDescriptor(imageUri,"r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        if (isModuleDevelop) {
            userAccount = Account(12,12,"username","password", LocalDateTime.now(), LocalDateTime.now(),0)
            userInfo = Person("nickname1000")
            store = Shop(12,12,"花尖","广州天河中山大道","https://gw.alicdn.com/tps/i3/TB1yeWeIFXXXXX5XFXXuAZJYXXX-210-210.png_50x50.jpg")
            TOKEN = "hello_zhangsan_1"
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        QMUISwipeBackActivityManager.init(this)
    }





}