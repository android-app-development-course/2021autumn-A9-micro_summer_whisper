package com.micro_summer_whisper.flower_supplier.common

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.alibaba.android.arouter.launcher.ARouter
import com.micro_summer_whisper.flower_supplier.common.pojo.Store
import com.micro_summer_whisper.flower_supplier.common.pojo.UserAccount
import com.micro_summer_whisper.flower_supplier.common.pojo.UserInfo
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager


class FlowerSupplierApplication: Application() {

    companion object {
        lateinit var context: Context
        val ignoreNetworkFail = true //true，忽略网络请求失败；false，正常
        val isDebug = true
        val isModuleDevelop= true
        var isLogin = false
        lateinit var userAccount: UserAccount
        lateinit var userInfo: UserInfo
        lateinit var store: Store
        fun getBitmapFormUri(imageUri: Uri) = context.contentResolver.openFileDescriptor(imageUri,"r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        if (isModuleDevelop) {
            userAccount = UserAccount("",1000)
            userInfo = UserInfo("nickname1000")
            store = Store(1,"https://gw.alicdn.com/tps/i3/TB1yeWeIFXXXXX5XFXXuAZJYXXX-210-210.png_50x50.jpg","花尖","广州天河中山大道")
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        QMUISwipeBackActivityManager.init(this)
    }





}