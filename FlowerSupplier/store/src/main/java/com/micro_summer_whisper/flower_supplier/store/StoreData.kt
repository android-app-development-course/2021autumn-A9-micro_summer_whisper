package com.micro_summer_whisper.flower_supplier.store

class StoreData {

    companion object StoreDataType {
        const val SIX_TEXT_BOX = 0
    }
    var dataType = 0
    lateinit var sixTextBoxTitleList: ArrayList<String>
    lateinit var sixTextBoxContentList: ArrayList<String>

}

class StoreDataBuilder {

    private var storeData = StoreData()

    fun setSixTextBoxTitleList(sixTextBoxTitleList: ArrayList<String>): StoreDataBuilder {
        storeData.sixTextBoxTitleList = sixTextBoxTitleList
        return this
    }

    fun setSixTextBoxContentList(sixTextBoxContentList: ArrayList<String>): StoreDataBuilder{
        storeData.sixTextBoxContentList = sixTextBoxContentList
        return this
    }

    fun setStoreDataType(dataType: Int): StoreDataBuilder{
        storeData.dataType = dataType
        return this
    }

    fun build(): StoreData {
        val res = storeData
        storeData = StoreData()
        return res
    }

}