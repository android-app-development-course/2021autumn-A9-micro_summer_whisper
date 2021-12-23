package com.micro_summer_whisper.flower_supplier.common.network

import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.pojo.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("shop/getStoreDash/{id}")
    fun getStoreDash(@Path("id") id: Int): Call<ApiResponse<StoreDash>>

    @GET("shop/getStoreInfo/{id}")
    fun getStoreInfo(@Path("id") id: Int): Call<ApiResponse<Shop>>

    @POST("shop/updateStoreInfo")
    fun updateStoreInfo(@Body body: Shop): Call<ApiResponse<Shop>>

    @GET("category/getCategoryList")
    fun getGoodCategoryList(): Call<ApiResponse<List<CategoryVo>>>

    @GET("product/getProductList")
    fun getGoodList(@Query("condition_type") condition_type: String, @Query("condition") condition: String): Call<ApiResponse<List<ProductVo>>>


    @POST("product/saveOrUpdateProduct")
    fun newGood(@Body body: ProductVo): Call<ApiResponse<ProductVo>>

    @POST("product/saveOrUpdateProduct")
    fun updateGood(@Body body: ProductVo): Call<ApiResponse<ProductVo>>

    @POST("product/removeProduct")
    fun removeGood(@Body body: ProductVo): Call<ApiResponse<Unit>>


    @GET("getChattingMsgxx")
    fun getChattingMsg(): Call<ArrayList<ChattingMsg>>

    @POST("sendChattingMsg")
    fun sendChattingMsg(@Body body: ChattingMsg): Call<Int>

    @GET("productOrder/getOrderList")
    fun getOrderList(@Query("orderState") orderState: Int,@Query("shopId") shopId: Int): Call<ApiResponse<OrderVo>>

    @POST("productOrder/updateOrder")
    fun updateOrder(@Query("orderId") orderId: Int, @Query("orderState") orderState: Int?, @Query("logisticsOrderNumber") logisticsOrderNumber: String?): Call<ApiResponse<OrderVo>>

    @POST("login")
    fun login(@Body body: Account): Call<ApiResponse<Person>>

    @POST("register")
    fun register(@Body body: Account): Call<ApiResponse<Account>>

    @GET("getUserInfo")
    fun getUserInfo(): Call<ApiResponse<Person>>

    @POST("updateUserInfo")
    fun updateUserInfo(@Body body: Person): Call<ApiResponse<Person>>


}