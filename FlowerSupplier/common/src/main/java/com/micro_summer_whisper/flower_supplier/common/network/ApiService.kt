package com.micro_summer_whisper.flower_supplier.common.network

import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.pojo.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("getStoreDash")
    fun getStoreDash(): Call<Map<String,String>>

    @GET("getStoreInfo")
    fun getStoreInfo(): Call<Store>

    @POST("updateStoreInfo")
    fun updateStoreInfo(@Body body: Store): Call<Store>

    @GET("getGoodCategoryList")
    fun getGoodCategoryList(): Call<List<String>>

    @GET("getGoodList")
    fun getGoodList(@Query("category") category: String, @Query("keyword") keyword: String = ""): Call<List<Good>>

    @POST("newGood")
    fun newGood(@Body body: Good): Call<Map<String,String>>

    @POST("updateGood")
    fun updateGood(@Body body: Good): Call<Map<String,String>>

    @GET("getChattingMsgxx")
    fun getChattingMsg(): Call<ArrayList<ChattingMsg>>

    @POST("sendChattingMsg")
    fun sendChattingMsg(@Body body: ChattingMsg): Call<Map<String,String>>

    @GET("getOrderList")
    fun getOrderList(@Query("category") category: String): Call<List<Order>>

    @POST("updateOrder")
    fun updateOrder(@Body body: Order): Call<Map<String,String>>

    @POST("login")
    fun login(@Body body: UserAccount): Call<UserInfo>

    @POST("register")
    fun register(@Body body: UserAccount): Call<Map<String,String>>

    @GET("getUserInfo")
    fun getUserInfo(): Call<UserInfo>

    @POST("updateUserInfo")
    fun updateUserInfo(@Body body: UserInfo): Call<Map<String,String>>


}