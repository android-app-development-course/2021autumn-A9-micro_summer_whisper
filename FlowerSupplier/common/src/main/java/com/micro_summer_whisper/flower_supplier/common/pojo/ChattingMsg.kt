package com.micro_summer_whisper.flower_supplier.common.pojo

import java.io.Serializable

class ChattingMsg(val headImageLink: String, val content: ByteArray, val msgType: Int, val aId: Long,val bId: Long,val nickName: String,val isText: Boolean): Serializable {
    companion object MsgType {
        const val TYPE_SEND_TEXT = 1
        const val TYPE_SEND_PICTURE = 2
        const val TYPE_RECEIVE_TEXT = 3
        const val TYPE_RECEIVE_PICTURE = 4
    }
}