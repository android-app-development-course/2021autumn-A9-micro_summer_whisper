package com.micro_summer_whisper.flower_supplier.common.network

import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.conditon.OrderCondition
import com.micro_summer_whisper.flower_supplier.common.conditon.ProductCondition
import com.micro_summer_whisper.flower_supplier.common.pojo.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("shop/getStoreDash/{id}")
    fun getStoreDash(@Path("id") id: Int): Call<ApiResponse<StoreDash>>

    @GET("shop/getStoreInfo/{id}")
    fun getStoreInfo(@Path("id") id: Int): Call<ApiResponse<Shop>>

    @GET("shop/hasStore/{userId}")
    fun hasStore(@Path("userId") userId: Int): Call<ApiResponse<Shop>>

    @POST("shop/createStore")
    fun createStore(@Body body: Shop): Call<ApiResponse<Shop>>


    @POST("shop/updateStoreInfo")
    fun updateStoreInfo(@Body body: Shop): Call<ApiResponse<Shop>>

    @POST("category/getCategoryList")
    fun getGoodCategoryList(@Body categoryCondition: CategoryCondition): Call<ApiResponse<List<CategoryVo>>>

    @POST("product/getProductList")
    fun getGoodList(@Body body: ProductCondition): Call<ApiResponse<List<ProductVo>>>


    @POST("product/saveOrUpdateProduct")
    fun saveOrUpdateGood(@Body body: ProductVo): Call<ApiResponse<ProductVo>>

    @POST("product/removeProduct/{id}")
    fun removeGood(@Path("id") id: Int): Call<ApiResponse<Any>>


    @GET("chat/getMessage/{userId}")
    fun getChattingMsg(@Path("userId") userId: Int): Call<ApiResponse<ArrayList<ChatMessageVo>>>

    @POST("chat/sendMessage")
    fun sendChattingMsg(@Body body: ChatMessageVo): Call<ApiResponse<Any>>

    @POST("productOrder/getOrderList")
    fun getOrderList(@Body orderCondition: OrderCondition): Call<ApiResponse<List<OrderVo>>>

    @POST("productOrder/updateOrder")
    fun updateOrder(
        @Query("orderId") orderId: Int,
        @Query("orderState") orderState: Int?,
        @Query("logisticsOrderNumber") logisticsOrderNumber: String?
    ): Call<ApiResponse<OrderVo>>

    @GET("account/tCode")
    fun getCode(@Query("email") email: String): Call<ApiResponse<Any>>

    @POST("account/tRegister")
    fun register(@Body personVo: PersonVo): Call<ApiResponse<PersonVo>>


    @POST("account/tLoginByPassword")
    fun login(@Body personVo: PersonVo): Call<ApiResponse<PersonVo>>


    @GET("person/tInfo")
    fun info(@Query("id") id: Int): Call<ApiResponse<PersonVo>>

    @POST("person/tUpdate")
    fun update(@Body personVo: PersonVo): Call<ApiResponse<Any>>
}