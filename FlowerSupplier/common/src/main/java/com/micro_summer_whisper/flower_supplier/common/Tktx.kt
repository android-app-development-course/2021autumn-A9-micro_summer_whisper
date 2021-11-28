package com.micro_summer_whisper.flower_supplier.common

import android.view.Gravity
import android.widget.Toast

fun String.shortToast() {
    val toast = Toast.makeText(FlowerSupplierApplication.context, this, Toast.LENGTH_SHORT)
    toast.show()
}

fun String.longToast() {
    val toast = Toast.makeText(FlowerSupplierApplication.context, this, Toast.LENGTH_LONG)
    toast.show()
}
