package com.micro_summer_whisper.flower_supplier.store

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Store
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.common.util.PictureUtils
import com.micro_summer_whisper.flower_supplier.store.databinding.ActivityMainBinding
import com.micro_summer_whisper.flower_supplier.store.databinding.ActivityStoreSettingBinding
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.RuntimeException

class StoreSettingActivity : BaseActivity() {

    private val apiService = ServiceCreator.create(ApiService::class.java)

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
        initStoreSetting()
        binding.storeSettingNameC.setOnClickListener {
            val editText = EditText(this)
            val store = FlowerSupplierApplication.store
            editText.setText(store.name.toString())
            val inputDialog = AlertDialog.Builder(this)
            inputDialog.setTitle("输入新的店铺名称").setView(editText)
            inputDialog.setPositiveButton("保存",
                DialogInterface.OnClickListener { dialog, which ->
                    val gStore = FlowerSupplierApplication.store
                    updateStoreInfo(Store(gStore.storeId,"https://gw.alicdn.com/tps/i3/TB1yeWeIFXXXXX5XFXXuAZJYXXX-210-210.png_50x50.jpg",editText.text.toString(),gStore.address))
                }).show()
        }
        binding.storeSettingAddressC.setOnClickListener {
            val editText = EditText(this)
            val store = FlowerSupplierApplication.store
            editText.setText(store.address.toString())
            val inputDialog = AlertDialog.Builder(this)
            inputDialog.setTitle("输入新的店铺地址").setView(editText)
            inputDialog.setPositiveButton("保存",
                DialogInterface.OnClickListener { dialog, which ->
                    val gStore = FlowerSupplierApplication.store
                    updateStoreInfo(Store(gStore.storeId,"https://gw.alicdn.com/tps/i3/TB1yeWeIFXXXXX5XFXXuAZJYXXX-210-210.png_50x50.jpg",gStore.name,editText.text.toString()))
                }).show()
        }
        binding.storeSettingHeadImageC.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }
    }

    private fun initStoreSetting(){
        val thisthis = this
        apiService.getStoreInfo().enqueue(object : Callback<Store> {
            override fun onResponse(call: Call<Store>, response: Response<Store>) {
                if (FlowerSupplierApplication.isDebug) {
                    onFailure(call, RuntimeException())
                } else {
                    val store = response.body()
                    store?.let {
                        FlowerSupplierApplication.store = store
                        Glide.with(thisthis).load(store.headImageLink).into(binding.storeSettingHeadImageB)
                        binding.storeSettingNameB.setText(store.name)
                        binding.storeSettingAddressB.setText(store.address)
                    }
                }
            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                if (FlowerSupplierApplication.isDebug){
                    val store = FlowerSupplierApplication.store
                    Glide.with(thisthis).load(store.headImageLink).into(binding.storeSettingHeadImageB)
                    binding.storeSettingNameB.setText(store.name)
                    binding.storeSettingAddressB.setText(store.address)
                } else {
                    Log.e(javaClass.simpleName,"获取店铺信息失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                    "获取店铺信息失败".shortToast()
                }
            }


        })
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
                    data?.data?.let {  uri ->
                        val bitmap = getBitmapFormUri(uri)
                        val gStore = FlowerSupplierApplication.store
                        updateStoreInfo(Store(gStore.storeId,"https://cdn2.jianshu.io/assets/default_avatar/2-9636b13945b9ccf345bc98d0d81074eb.jpg",gStore.name,gStore.address))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun updateStoreInfo(store: Store){
        val thisthis = this
        apiService.updateStoreInfo(store).enqueue(object : Callback<Store>{
            override fun onResponse(call: Call<Store>, response: Response<Store>) {
                if (FlowerSupplierApplication.isDebug) {
                    onFailure(call,RuntimeException())
                } else {
                    val newStore = response.body()
                    newStore?.let {
                        FlowerSupplierApplication.store = newStore
                        binding.storeSettingNameB.setText(newStore.name)
                        Glide.with(thisthis).load(newStore.headImageLink).into(binding.storeSettingHeadImageB)
                        binding.storeSettingAddressB.setText(newStore.address)
                    }
                }
            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                if (FlowerSupplierApplication.isDebug){
                    FlowerSupplierApplication.store = store
                    binding.storeSettingNameB.setText(store.name)
                    Glide.with(thisthis).load(store.headImageLink).into(binding.storeSettingHeadImageB)
                    binding.storeSettingAddressB.setText(store.address)
                } else {
                    Log.e(javaClass.simpleName,"更新店铺信息失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                    "更新店铺信息失败".shortToast()
                }
            }
        })
    }


}