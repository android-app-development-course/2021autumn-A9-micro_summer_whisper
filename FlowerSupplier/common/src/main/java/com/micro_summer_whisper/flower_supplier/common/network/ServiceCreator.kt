package com.micro_summer_whisper.flower_supplier.common.network


import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceCreator {

    private const val BASE_URL = "https://www.baidu.com/"


    fun <T> create(serviceClass: Class<T>): T {
        val httpClient = OkHttpClient.Builder() // 设置拦截器，添加统一的请求头
            .addInterceptor { chain -> // 以拦截到的请求为基础创建一个新的请求对象，然后插入Header
                val request: Request = chain.request().newBuilder()
                    .addHeader("token", FlowerSupplierApplication.userAccount.token)
                    .build()
                // 开始请求
                chain.proceed(request)
            }
            .build()
        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(serviceClass)
    }

}