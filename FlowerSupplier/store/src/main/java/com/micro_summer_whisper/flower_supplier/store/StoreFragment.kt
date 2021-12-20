package com.micro_summer_whisper.flower_supplier.store

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Good
import com.micro_summer_whisper.flower_supplier.common.pojo.Store
import com.micro_summer_whisper.flower_supplier.store.databinding.FragmentStoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import kotlin.random.Random


class StoreFragment : Fragment() {


    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!
    private val apiService = ServiceCreator.create(ApiService::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root
        init()
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

    override fun onResume() {
        super.onResume()
        init()
    }

    companion object {
        fun newInstance() =
            StoreFragment().apply {

            }
    }

    private fun init(){
        val thisthis = this
        apiService.getStoreInfo().enqueue(object : Callback<Store>{
            override fun onResponse(call: Call<Store>, response: Response<Store>) {
                if (FlowerSupplierApplication.isDebug) {
                    onFailure(call,RuntimeException())
                } else {
                    val store = response.body()
                    store?.let {
                        FlowerSupplierApplication.store = store
                        Glide.with(thisthis).load(store.headImageLink).into(binding.storeHeadImage)
                        binding.storeName.setText(store.name)
                    }
                }
            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                if (FlowerSupplierApplication.isDebug){
                    val store = FlowerSupplierApplication.store
                    Glide.with(thisthis).load(store.headImageLink).into(binding.storeHeadImage)
                    binding.storeName.setText(store.name)
                } else {
                    Log.e(javaClass.simpleName,"获取店铺信息失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                }
            }


        })
        apiService.getStoreDash().enqueue(object : Callback<Map<String,String>>{
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (FlowerSupplierApplication.isDebug) {
                    onFailure(call,RuntimeException())
                } else {
                    val dashM = response.body()
                    dashM?.let {
                        binding.storeDataYesterdayVisitB.setText(dashM["yesterdayVisit"])
                        binding.storeDataTodayVisitB.setText(dashM["todayVisit"])
                        binding.storeDataYesterdayOrderB.setText(dashM["yesterdayOrder"])
                        binding.storeDataTodayOrderB.setText(dashM["todayOrder"])
                        binding.storeDataWaitPayB.setText(dashM["waitPay"])
                        binding.storeDataWaitConfirmReceiveB.setText(dashM["waitConfirmReceive"])
                        binding.storeDataWaitSendGoodB.setText(dashM["waitSendGood"])
                    }
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                if (FlowerSupplierApplication.isDebug){
                    binding.storeDataYesterdayVisitB.setText("101")
                    binding.storeDataTodayVisitB.setText("102")
                    binding.storeDataYesterdayOrderB.setText("103")
                    binding.storeDataTodayOrderB.setText("104")
                    binding.storeDataWaitPayB.setText("105")
                    binding.storeDataWaitConfirmReceiveB.setText("106")
                    binding.storeDataWaitSendGoodB.setText("107")
                } else {
                    Log.e(javaClass.simpleName,"获取店铺仪表信息失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                }
            }

        })

    }
}