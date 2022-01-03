package com.micro_summer_whisper.flower_supplier.chat

import android.os.IBinder

interface IListBinder<T> : IBinder{
    fun getList():T
}