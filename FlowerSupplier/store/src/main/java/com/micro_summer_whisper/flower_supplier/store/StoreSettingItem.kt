package com.micro_summer_whisper.flower_supplier.store

class StoreSettingItem(val type: Int, val leftText: String, var rightText: String?, val imageLink: String?) {
    companion object StoreSettingItemType{
        const val TEXT = 1
        const val IMAGE = 2
    }
}