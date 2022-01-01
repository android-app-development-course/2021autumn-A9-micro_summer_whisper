package com.micro_summer_whisper.flower_supplier.me

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.longToast
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.OrderVo
import com.micro_summer_whisper.flower_supplier.common.pojo.Person
import com.micro_summer_whisper.flower_supplier.common.pojo.ProductVo
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.me.databinding.ActivityAccountUpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountUpdateActivity : BaseActivity() {
    private lateinit var binding: ActivityAccountUpdateBinding

    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, AccountUpdateActivity::class.java)
            context.startActivity(intent)
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.meSaveAccountUpdate.setOnClickListener {
            val newName = binding.meNewName.text.toString()

            FlowerSupplierApplication.userInfo.name = newName
            FlowerSupplierApplication.userInfo.password = null
            apiService.update(FlowerSupplierApplication.userInfo).enqueue(object :
                Callback<ApiResponse<Any>> {
                override fun onResponse(
                    call: Call<ApiResponse<Any>>,
                    response: Response<ApiResponse<Any>>
                ) {
                    val apiResponse = response.body() as ApiResponse<Any>
                    if (apiResponse.success) {
                        Log.d(javaClass.simpleName,"修改信息成功 ${it.toString()}")
                        "修改信息成功".shortToast()
                    } else {
                        Log.e(javaClass.simpleName,apiResponse.message)
                    }
                }
                override fun onFailure(call: Call<ApiResponse<Any>>, t: Throwable) {
                    "修改信息失败".shortToast()
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                }
            })
        }
    }
}