package com.micro_summer_whisper.flower_supplier.chat

class ChattingMsg(val imageId: Int, val content: String, val msgType: Int) {
    companion object MsgType {
        const val TYPE_SEND = 1
        const val TYPE_RECEIVE = 2
    }
}