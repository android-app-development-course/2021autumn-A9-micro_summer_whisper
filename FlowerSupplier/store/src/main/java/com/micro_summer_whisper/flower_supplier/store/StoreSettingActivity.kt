package com.micro_summer_whisper.flower_supplier.store

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.store.databinding.ActivityMainBinding
import com.micro_summer_whisper.flower_supplier.store.databinding.ActivityStoreSettingBinding

class StoreSettingActivity : BaseActivity() {

    private val storeSettingItemList = ArrayList<StoreSettingItem>()
    private lateinit var storeSettingAdapter: StoreSettingAdapter

    companion object {

        fun actionStart(context: Context) {
            val intent = Intent(context,StoreSettingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityStoreSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.storeSettingToolbar)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayHomeAsUpEnabled(true)
        }
        initStoreSettingItemList()
        storeSettingAdapter = StoreSettingAdapter(this ,storeSettingItemList)
        binding.storeSettingRecyclerview.adapter = storeSettingAdapter
        val layoutManager1 = LinearLayoutManager(this)
        binding.storeSettingRecyclerview.layoutManager = layoutManager1

    }

    private fun initStoreSettingItemList(){
        storeSettingItemList.clear()
        storeSettingItemList.add(StoreSettingItem(StoreSettingItem.TEXT, "店铺名称", "大龙零食",null))
        storeSettingItemList.add(StoreSettingItem(StoreSettingItem.IMAGE, "店铺头像", null,"https://gw.alicdn.com/tps/i3/TB1yeWeIFXXXXX5XFXXuAZJYXXX-210-210.png_50x50.jpg"))
        storeSettingItemList.add(StoreSettingItem(StoreSettingItem.TEXT, "店铺地址", "广州市天河区",null))
    }


}