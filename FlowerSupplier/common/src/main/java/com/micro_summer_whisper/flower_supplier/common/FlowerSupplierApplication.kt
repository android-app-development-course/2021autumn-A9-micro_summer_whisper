package com.micro_summer_whisper.flower_supplier.common

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter

class FlowerSupplierApplication: Application() {

    companion object {
        lateinit var context: Context
        val ignoreNetworkFail = true //true，忽略网络请求失败；false，正常
        val isDebug = true
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

}