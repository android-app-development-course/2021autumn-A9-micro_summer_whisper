package com.micro_summer_whisper.flower_supplier.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.micro_summer_whisper.flower_supplier.common.pojo.ChattingMsg

class ChatViewModel(chattingMsgList: MutableList<ChattingMsg>) {

    val msgList: LiveData<MutableList<ChattingMsg>> get() = _msgList
    private val _msgList = MutableLiveData<MutableList<ChattingMsg>>()
    init {
        _msgList.value = chattingMsgList
    }

    fun clearChatList(){
        _msgList.value?.clear()
    }

    fun coverChatList(chattingMsgList: List<ChattingMsg>){
        _msgList.value?.let {
            it.clear()
            it.addAll(chattingMsgList)
        }
    }

}