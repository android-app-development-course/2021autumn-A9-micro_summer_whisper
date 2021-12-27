package com.micro_summer_whisper.flower_supplier.order

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.conditon.OrderCondition
import com.micro_summer_whisper.flower_supplier.common.longToast
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.OrderVo
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.order.databinding.Fragment1Binding

import com.micro_summer_whisper.flower_supplier.order.databinding.Fragment3Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Fragment3 : Fragment() {
    private var _binding: Fragment3Binding? = null
    private val binding get() = _binding!!
    private val orderList = ArrayList<OrderVo>()
    private lateinit var orderAdapter: OrderAdapter
    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = Fragment3Binding.inflate(inflater, container, false)
        val view = binding.root

        activity?.let {
            this.context?.let {
                orderAdapter = OrderAdapter(it ,orderList)
                binding.orderRecyclerview3.adapter = orderAdapter
                val layoutManager = LinearLayoutManager(it)
                binding.orderRecyclerview3.layoutManager = layoutManager
            }
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Fragment3().apply {

            }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        initOrders()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initOrders() {
        orderList.clear()
        val oc = OrderCondition()

        oc.shopId = 12
        oc.orderState = 3

        apiService.getOrderList(oc).enqueue(object : Callback<ApiResponse<List<OrderVo>>> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<ApiResponse<List<OrderVo>>>,
                response: Response<ApiResponse<List<OrderVo>>>
            ) {
                val apiResponse = response.body() as ApiResponse<List<OrderVo>>
                if (apiResponse.success) {
                    apiResponse.data?.let {
                        //更新ui
                        orderList.addAll(it)
                        orderList.size.toString().shortToast()
                        orderAdapter.notifyDataSetChanged()
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
