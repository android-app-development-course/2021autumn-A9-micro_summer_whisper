package com.micro_summer_whisper.flower_supplier.store

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Shop
import com.micro_summer_whisper.flower_supplier.common.pojo.StoreDash
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.store.databinding.FragmentStoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException


class StoreFragment : Fragment() {


    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!
    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.storeHeadImage.setOnClickListener {
            this.context?.let {
                StoreSettingActivity.actionStart(it)
            }
        }
        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        if (FlowerSupplierApplication.isLogin&&FlowerSupplierApplication.curBottomNavIndex==FlowerSupplierApplication.STORE_INDEX){
            initStore()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden&&FlowerSupplierApplication.isLogin){
            initStore()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initStore(){
        val thisthis = this
        apiService.hasStore(FlowerSupplierApplication.userInfo.userId).enqueue(object : Callback<ApiResponse<Shop>>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<ApiResponse<Shop>>,
                response: Response<ApiResponse<Shop>>
            ) {
                val apiResponse = response.body() as ApiResponse<Shop>
                if (apiResponse.success){
                    val store = apiResponse.data
                    if (store==null){
                        thisthis.context?.let {
                            val notifyLoginDialog = AlertDialog.Builder(it)
                            notifyLoginDialog.setPositiveButton("????????????") { dialog, which ->
                                createStore()
                            }
                            notifyLoginDialog.show()
                        }
                    }
                    store?.let {
                        FlowerSupplierApplication.store = store
                        init()
                    }
                } else {
                    Log.e(javaClass.simpleName,apiResponse.message)
                }
            }

            override fun onFailure(call: Call<ApiResponse<Shop>>, t: Throwable) {
                Log.e(javaClass.simpleName,"??????????????????????????????")
                Log.e(javaClass.simpleName,t.stackTraceToString())
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createStore(){
        val shop = Shop()
        shop.priority = 0
        shop.shopName = ""
        shop.shopAddress = ""
        shop.ownerId = FlowerSupplierApplication.userInfo.userId
        apiService.createStore(shop).enqueue(object : Callback<ApiResponse<Shop>>{
            override fun onResponse(
                call: Call<ApiResponse<Shop>>,
                response: Response<ApiResponse<Shop>>
            ) {
                val apiResponse = response.body() as ApiResponse<Shop>
                if (apiResponse.success){
                    val store = apiResponse.data
                    store?.let {
                        FlowerSupplierApplication.store = store
                        "????????????".shortToast()
                    }
                } else {
                    "??????????????????".shortToast()
                    Log.d(javaClass.simpleName,apiResponse.message)
                }
            }

            override fun onFailure(call: Call<ApiResponse<Shop>>, t: Throwable) {
                Log.e(javaClass.simpleName,"??????????????????")
                "??????????????????".shortToast()
                Log.e(javaClass.simpleName,t.stackTraceToString())

            }

        })

    }

    companion object {
        fun newInstance() =
            StoreFragment().apply {

            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun init(){
        val thisthis = this
        apiService.getStoreInfo(FlowerSupplierApplication.store.shopId).enqueue(object : Callback<ApiResponse<Shop>>{
            override fun onResponse(call: Call<ApiResponse<Shop>>, response: Response<ApiResponse<Shop>>) {

                val apiResponse = response.body() as ApiResponse<Shop>
                if (apiResponse.success){
                    val store = apiResponse.data
                    store?.let {
                        FlowerSupplierApplication.store = store
                        Glide.with(thisthis).load(store.shopImg).into(binding.storeHeadImage)
                        binding.storeName.setText(store.shopName)
                    }

                } else {
                    Log.d(javaClass.simpleName,apiResponse.message)
                }

            }

            override fun onFailure(call: Call<ApiResponse<Shop>>, t: Throwable) {

                    Log.e(javaClass.simpleName,"????????????????????????")
                    Log.e(javaClass.simpleName,t.stackTraceToString())

            }


        })
        apiService.getStoreDash(FlowerSupplierApplication.store.shopId).enqueue(object : Callback<ApiResponse<StoreDash>>{
            override fun onResponse(
                call: Call<ApiResponse<StoreDash>>,
                response: Response<ApiResponse<StoreDash>>
            ) {
                val apiResponse = response.body() as ApiResponse<StoreDash>
                if (apiResponse.success){
                    val dashM = apiResponse.data
                    dashM?.let {
                        binding.storeDataYesterdayOrderB.setText(dashM.yesterdayOrder.toString())
                        binding.storeDataTodayOrderB.setText(dashM.todayOrder.toString())
                        binding.storeDataWaitPayB.setText(dashM.waitPay.toString())
                        binding.storeDataWaitConfirmReceiveB.setText(dashM.waitConfirmGood.toString())
                        binding.storeDataWaitSendGoodB.setText(dashM.waitSendGood.toString())
                    }
                } else {
                    Log.d(javaClass.simpleName,apiResponse.message)
                }


            }

            override fun onFailure(call: Call<ApiResponse<StoreDash>>, t: Throwable) {

                    Log.e(javaClass.simpleName,"??????????????????????????????")
                    Log.e(javaClass.simpleName,t.stackTraceToString())

            }

        })

    }
}