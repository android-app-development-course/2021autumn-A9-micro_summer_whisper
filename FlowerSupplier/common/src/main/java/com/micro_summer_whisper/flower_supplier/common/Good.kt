package com.micro_summer_whisper.flower_supplier.common


import java.io.Serializable

class Good(val goodId: Long, val storeId: Long, val coverUri: String, val pictureUriList: List<String>,
           val title: String, val price: Double, val stock: Long,
           val detail: String
           ): Serializable {

}