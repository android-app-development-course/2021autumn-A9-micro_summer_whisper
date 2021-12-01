package com.micro_summer_whisper.flower_supplier.common


import android.widget.Toast
import java.lang.StringBuilder
import kotlin.random.Random

fun String.shortToast() {
    val toast = Toast.makeText(FlowerSupplierApplication.context, this, Toast.LENGTH_SHORT)
    toast.show()
}

fun String.longToast() {
    val toast = Toast.makeText(FlowerSupplierApplication.context, this, Toast.LENGTH_LONG)
    toast.show()
}

fun String.randomTimes(bound: Int): String {
    val sb = StringBuilder()
    val a = Random.nextInt(bound)
    for (i in 0..a) {
        sb.append(this)
    }
    return sb.toString()
}