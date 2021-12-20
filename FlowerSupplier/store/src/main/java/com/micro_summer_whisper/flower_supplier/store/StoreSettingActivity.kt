package com.micro_summer_whisper.flower_supplier.store

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.store.databinding.ActivityMainBinding
import com.micro_summer_whisper.flower_supplier.store.databinding.ActivityStoreSettingBinding
import java.lang.Exception

class StoreSettingActivity : BaseActivity() {

    private val storeSettingItemList = ArrayList<StoreSettingItem>()
    private lateinit var storeSettingAdapter: StoreSettingAdapter

    companion object {
        const val IMAGE_REQUEST_CODE = 1
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST_CODE -> if (resultCode === RESULT_OK) { //resultcode是setResult里面设置的code值
                try {
                    data?.let {
                        val selectedImage: Uri? = it.getData() //获取系统返回的照片的Uri
                        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                        val cursor: Cursor? = selectedImage?.let { it1 ->
                            contentResolver.query(
                                it1,
                                filePathColumn, null, null, null
                            )
                        }
                        cursor?.let {
                            it.moveToFirst()
                            val columnIndex: Int = it.getColumnIndex(filePathColumn[0])
                            val path = it.getString(columnIndex) //获取照片路径
                            it.close()
                            val bitmap = BitmapFactory.decodeFile(path)
                            storeSettingItemList[1].imageLink = "https://cdn2.jianshu.io/assets/default_avatar/1-04bbeead395d74921af6a4e8214b4f61.jpg"
                            storeSettingAdapter.notifyItemChanged(1)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


}