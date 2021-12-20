package com.micro_summer_whisper.flower_supplier.common

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDatabaseHelper(private val context: Context, private val name: String, private val version: Int):SQLiteOpenHelper(context,name,null,version) {

    private val createChats = "create table chats( id integer primary key autoincrement,"+
            "b_id BIGINT, a_id BIGINT, content text, type TINYINT, created_time text, head_image_link text, nick_name text, is_text TINYINT);"

    private val createLatestChats = "create table latest_chats( id integer primary key autoincrement,"+
            "b_id BIGINT, a_id BIGINT, content text, type TINYINT, created_time text, head_image_link text, nick_name text, is_text TINYINT);"

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(javaClass.name,"onCreate")
        db?.apply {
            execSQL(createChats)
            execSQL(createLatestChats)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(javaClass.name,"onUpgrade")
    }
}