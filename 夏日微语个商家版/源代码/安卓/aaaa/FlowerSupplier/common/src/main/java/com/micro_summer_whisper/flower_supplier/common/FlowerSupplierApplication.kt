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
import com.micro_summer_whisper.flower_supplier.common.pojo.PersonVo
import com.micro_summer_whisper.flower_supplier.common.pojo.Shop
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import java.time.LocalDateTime


class FlowerSupplierApplication : Application() {

    companion object {
        var curBottomNavIndex = 0
        const val STORE_INDEX = 0
        const val GOOD_INDEX = 1
        const val ORDER_INDEX = 2
        const val CHAT_INDEX = 3
        const val ME_INDEX = 4
        lateinit var context: Context
        val isDebug = true
        var isLogin = false
        var UserId = -1
        lateinit var userInfo: PersonVo
        lateinit var store: Shop
        var TOKEN: String = ""
        fun getBitmapFormUri(imageUri: Uri) =
            context.contentResolver.openFileDescriptor(imageUri, "r")?.use {
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

//        userAccount = Account(-1,-1,"username","password", LocalDateTime.now(), LocalDateTime.now(),0)
//        val person = Person()
//        person.userId = -1
//        person.name = "张三"
//        userInfo = person
//        val shop = Shop()
//        shop.shopId = -1
//        shop.ownerId = -1
//        store = shop
//        TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjY2NjYiLCJzdWIiOiJoaSIsImlhdCI6MTY0MDI2NDczOSwiZXhwIjozNzY0MDI2NDczOSwicm9sZXMiOiJtZXJjaGFudCIsInVpZCI6MTJ9.7PuaxdcsckkNw5kN2gDHcroVituMxs-HIbZPfsHvvDo"

//        UserId = 19
//        val person = PersonVo()
//        person.userId = 19
//        person.name = "张三"
//        userInfo = person
//        val shop = Shop()
//        shop.shopId = 19
//        shop.ownerId = 19
//        store = shop
//        isLogin = true
//        TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjY2NjYiLCJzdWIiOiJoaSIsImlhdCI6MTY0MDI2NDczOSwiZXhwIjozNzY0MDI2NDczOSwicm9sZXMiOiJtZXJjaGFudCIsInVpZCI6MTJ9.7PuaxdcsckkNw5kN2gDHcroVituMxs-HIbZPfsHvvDo"


        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        QMUISwipeBackActivityManager.init(this)
    }


}