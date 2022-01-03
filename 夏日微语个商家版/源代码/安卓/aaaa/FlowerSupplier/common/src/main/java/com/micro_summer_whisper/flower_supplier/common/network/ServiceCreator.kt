package com.micro_summer_whisper.flower_supplier.common.network


import android.os.Build
import androidx.annotation.RequiresApi
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.util.DateTimeUtils
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceCreator {

    private const val BASE_URL = "http://10.242.101.94:8080/"


    @RequiresApi(Build.VERSION_CODES.O)
    fun <T> create(serviceClass: Class<T>): T {
        val httpClient = OkHttpClient.Builder() // 设置拦截器，添加统一的请求头
            .addInterceptor { chain -> // 以拦截到的请求为基础创建一个新的请求对象，然后插入Header
                val request: Request = chain.request().newBuilder()
                    .addHeader("token", FlowerSupplierApplication.TOKEN)
                    .build()
                // 开始请求
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(DateTimeUtils.newGsonInstance()))
            .build()
        return retrofit.create(serviceClass)
    }

    fun createRegisterService(): ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

}