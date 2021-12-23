package com.micro_summer_whisper.flower_supplier.store

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.view.drawToBitmap
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Shop
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.common.util.PictureUtils
import com.micro_summer_whisper.flower_supplier.store.databinding.ActivityStoreSettingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.RuntimeException

class StoreSettingActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    companion object {
        const val IMAGE_REQUEST_CODE = 1
        fun actionStart(context: Context) {
            val intent = Intent(context,StoreSettingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityStoreSettingBinding

    @RequiresApi(Build.VERSION_CODES.O)
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
            editText.setText(store.shopName.toString())
            val inputDialog = AlertDialog.Builder(this)
            inputDialog.setTitle("输入新的店铺名称").setView(editText)
            inputDialog.setPositiveButton("保存",
                DialogInterface.OnClickListener { dialog, which ->
                    val gStore = FlowerSupplierApplication.store
                    updateStoreInfo(Shop(gStore.shopId,gStore.ownerId,editText.text.toString(),null,null))
                }).show()
        }
        binding.storeSettingAddressC.setOnClickListener {
            val editText = EditText(this)
            val store = FlowerSupplierApplication.store
            editText.setText(store.shopAddress.toString())
            val inputDialog = AlertDialog.Builder(this)
            inputDialog.setTitle("输入新的店铺地址").setView(editText)
            inputDialog.setPositiveButton("保存",
                DialogInterface.OnClickListener { dialog, which ->
                    val gStore = FlowerSupplierApplication.store
                    updateStoreInfo(Shop(gStore.shopId,gStore.ownerId,null,editText.text.toString(),
                        null))
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initStoreSetting(){
        val thisthis = this
        apiService.getStoreInfo(FlowerSupplierApplication.store.shopId).enqueue(object : Callback<ApiResponse<Shop>> {
            override fun onResponse(call: Call<ApiResponse<Shop>>, response: Response<ApiResponse<Shop>>) {
                if (FlowerSupplierApplication.isDebug) {
                    onFailure(call, RuntimeException())
                } else {
                    val apiResponse = response.body() as ApiResponse<Shop>
                    val store = apiResponse.data
                    store?.let {
                        FlowerSupplierApplication.store = store
                        Glide.with(thisthis).load(store.shopImg).into(binding.storeSettingHeadImageB)
                        binding.storeSettingNameB.setText(store.shopName)
                        binding.storeSettingAddressB.setText(store.shopAddress)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<Shop>>, t: Throwable) {
                if (FlowerSupplierApplication.isDebug){
                    val store = FlowerSupplierApplication.store
                    Glide.with(thisthis).load(store.shopImg).into(binding.storeSettingHeadImageB)
                    binding.storeSettingNameB.setText(store.shopName)
                    binding.storeSettingAddressB.setText(store.shopAddress)
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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST_CODE -> if (resultCode === RESULT_OK) { //resultcode是setResult里面设置的code值
                try {
                    data?.data?.let {  uri ->
                        val bitmap = getBitmapFormUri(uri)
                        val gStore = FlowerSupplierApplication.store
                        binding.storeSettingHeadImageB.setImageBitmap(bitmap)
                        updateStoreInfo(Shop(gStore.shopId,null,null,null,
                            PictureUtils.bitmap2String(binding.storeSettingHeadImageB.drawToBitmap(),100)))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateStoreInfo(store: Shop){
        val thisthis = this
        apiService.updateStoreInfo(store).enqueue(object : Callback<ApiResponse<Shop>>{
            override fun onResponse(call: Call<ApiResponse<Shop>>, response: Response<ApiResponse<Shop>>) {
                val apiResponse = response.body() as ApiResponse<Shop>
                if (apiResponse.success){
                    val newStore = apiResponse.data
                    newStore?.let {
                        FlowerSupplierApplication.store = newStore
                        binding.storeSettingNameB.setText(newStore.shopName)
                        Glide.with(thisthis).load(newStore.shopImg).into(binding.storeSettingHeadImageB)
                        binding.storeSettingAddressB.setText(newStore.shopAddress)
                    }
                } else {
                    Log.d(javaClass.simpleName,apiResponse.message)
                }



            }

            override fun onFailure(call: Call<ApiResponse<Shop>>, t: Throwable) {

                    Log.e(javaClass.simpleName,"更新店铺信息失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                    "更新店铺信息失败".shortToast()
            }
        })
    }


}