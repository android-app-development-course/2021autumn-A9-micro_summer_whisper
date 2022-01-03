package com.example.floor_shop.utils

import android.content.Context

object LoginUtil {
    lateinit var context:Context

    fun isLogin(c :Context):Boolean {
        context = c
        return isLogin()
    }
    fun isLogin():Boolean {
        var data = context.getSharedPreferences("userInfo", 0)
        var account = data.getString("account", "")
        var password = data.getString("password", "")
        return account!!.length>0&&password!!.length>0
    }


}