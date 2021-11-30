package org.coderpwh.micro_summer_whisper.models

data class CommodityModel(
    val id: Long,val type:String,val name:String
    ,val price:String,val total:Int,val surplus:Int
    ,val isDeleted:Boolean,val info:String
    ,val img:String,val otherImgUrls:String)