package com.micro_summer_whisper.flower_supplier.order

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import cn.hutool.core.math.Money
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.conditon.OrderCondition
import com.micro_summer_whisper.flower_supplier.common.longToast
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.OrderVo
import com.micro_summer_whisper.flower_supplier.common.pojo.Person
import com.micro_summer_whisper.flower_supplier.common.pojo.ProductVo
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.order.databinding.ActivityOrderDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityOrderDetailBinding

    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    companion object {
        const val ORDER_ID = "order_id"
        const val SHOP_ID = "shop_id"
        fun actionStart(context: Context) {
            val intent = Intent(context, OrderDetailActivity::class.java)
            context.startActivity(intent)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val orderId = intent.getIntExtra(ORDER_ID, 0)
        val shopId = intent.getIntExtra(SHOP_ID, 0)

        getOrderDetail(orderId, shopId)



        binding.tvSaveOrder.setOnClickListener {
            val logisticsOrderNumber = binding.tvLogisticsOrderNumber.text.toString()
            val state = when (binding.tvOrderState.selectedItemPosition) {
                3 -> 0
                0 -> 1
                1 -> 2
                2 -> 3
                else -> -1
            }

            apiService.updateOrder(orderId, state, logisticsOrderNumber).enqueue(object :
                Callback<ApiResponse<OrderVo>> {
                override fun onResponse(
                    call: Call<ApiResponse<OrderVo>>,
                    response: Response<ApiResponse<OrderVo>>
                ) {
                    val apiResponse = response.body() as ApiResponse<OrderVo>
                    if (apiResponse.success) {
                        apiResponse.data?.let {
                            Log.d(javaClass.simpleName, "修改订单成功 ${it.toString()}")
//                            "修改订单成功".shortToast()
                        }

                    } else {
                        Log.d(javaClass.simpleName, apiResponse.message)
                    }
                }

                override fun onFailure(call: Call<ApiResponse<OrderVo>>, t: Throwable) {
                    "修改订单失败".shortToast()
                    Log.e(javaClass.simpleName, t.stackTraceToString())
                }
            })
            finish()


        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getOrderDetail(orderId: Int, shopId: Int) {
        val oc = OrderCondition()
        oc.orderId = orderId
        oc.shopId = shopId
        apiService.getOrderList(oc).enqueue(object : Callback<ApiResponse<List<OrderVo>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<OrderVo>>>,
                response: Response<ApiResponse<List<OrderVo>>>
            ) {
                val apiResponse = response.body() as ApiResponse<List<OrderVo>>
                if (apiResponse.success) {
                    apiResponse.data?.let {
                        //更新ui
                        binding.tvReceiverName.text = it[0].receiverName
                        Glide.with(binding.root).load(it[0].productImg).into(binding.tvImage)
                        binding.tvTitle.text = it[0].productName
                        binding.tvPrice.text = "￥" + Money(
                            it[0].onePrice.toLong() * it[0].amount / 100,
                            it[0].onePrice * it[0].amount % 100
                        ).toString()
                        binding.tvNum.text = "共" + it[0].amount + "件"
                        binding.tvOrderState.setSelection((it[0].orderState + 3) % 4)
                        binding.tvOrderAddress.text =
                            it[0].receiverName + ", " + it[0].receiverPhone + ", " + it[0].address
                        binding.tvLogisticsOrderNumber.setText(it[0].logisticsOrderNumber)
                        binding.tvCreateTime.text = it[0].createTime.toString()
                        Log.d(javaClass.simpleName, it.toString())
                    }
                } else {
                    Log.d(javaClass.simpleName, apiResponse.message)
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<OrderVo>>>, t: Throwable) {
                Log.e(javaClass.simpleName, "获取订单失败")
                Log.e(javaClass.simpleName, t.stackTraceToString())
            }


        })

    }

}