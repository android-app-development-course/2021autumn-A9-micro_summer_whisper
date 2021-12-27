package com.micro_summer_whisper.flower_supplier.order

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.micro_summer_whisper.flower_supplier.common.conditon.OrderCondition
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.OrderVo
import com.micro_summer_whisper.flower_supplier.order.*
import com.micro_summer_whisper.flower_supplier.order.databinding.FragmentOrderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrderFragment : Fragment()
{
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private val fragments = arrayOfNulls<Fragment>(5)
    private var currentIndex = 0


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        initFragment()
        binding.navigationFf0.setTextColor(Color.parseColor("#FF5500"))
        binding.navigationFf0.setTypeface(Typeface.DEFAULT_BOLD)

        binding.navigationFf0.setOnClickListener {
            activity?.let {
                binding.navigationFf0.setTextColor(Color.parseColor("#FF5500"))
                binding.navigationFf0.setTypeface(Typeface.DEFAULT_BOLD)

                val fragmentTran = it.supportFragmentManager.beginTransaction()
                binding.navigationFf1.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf2.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf3.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf4.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf4.setTypeface(Typeface.DEFAULT)
                binding.navigationFf1.setTypeface(Typeface.DEFAULT)
                binding.navigationFf2.setTypeface(Typeface.DEFAULT)
                binding.navigationFf3.setTypeface(Typeface.DEFAULT)

                hideAndShow(0, fragmentTran)
            }
        }
        binding.navigationFf1.setOnClickListener {
            activity?.let {
                binding.navigationFf1.setTextColor(Color.parseColor("#FF5500"))
                binding.navigationFf1.setTypeface(Typeface.DEFAULT_BOLD)
                val fragmentTran = it.supportFragmentManager.beginTransaction()
                if (fragments[1] == null) {
                    fragments[1] = Fragment1.newInstance()
                    fragments[1]?.let { fragmentTran.add(R.id.order_content, it, "Fragment1") }
                }
                binding.navigationFf0.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf2.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf3.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf4.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf4.setTypeface(Typeface.DEFAULT)
                binding.navigationFf0.setTypeface(Typeface.DEFAULT)
                binding.navigationFf2.setTypeface(Typeface.DEFAULT)
                binding.navigationFf3.setTypeface(Typeface.DEFAULT)
                hideAndShow(1, fragmentTran)
            }
        }
        binding.navigationFf2.setOnClickListener {
            activity?.let {
                binding.navigationFf2.setTextColor(Color.parseColor("#FF5500"))
                binding.navigationFf2.setTypeface(Typeface.DEFAULT_BOLD)
                val fragmentTran = it.supportFragmentManager.beginTransaction()
                if (fragments[2] == null) {
                    fragments[2] = Fragment2.newInstance()
                    fragments[2]?.let { fragmentTran.add(R.id.order_content, it, "Fragment2") }
                }
                binding.navigationFf0.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf1.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf3.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf4.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf4.setTypeface(Typeface.DEFAULT)
                binding.navigationFf0.setTypeface(Typeface.DEFAULT)
                binding.navigationFf1.setTypeface(Typeface.DEFAULT)
                binding.navigationFf3.setTypeface(Typeface.DEFAULT)
                hideAndShow(2, fragmentTran)
            }
        }
        binding.navigationFf3.setOnClickListener {
            activity?.let {
                binding.navigationFf3.setTextColor(Color.parseColor("#FF5500"))
                binding.navigationFf3.setTypeface(Typeface.DEFAULT_BOLD)
                val fragmentTran = it.supportFragmentManager.beginTransaction()
                if (fragments[3] == null) {
                    fragments[3] = Fragment3.newInstance()
                    fragments[3]?.let { fragmentTran.add(R.id.order_content, it, "Fragment3") }
                }
                binding.navigationFf0.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf2.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf1.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf4.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf4.setTypeface(Typeface.DEFAULT)
                binding.navigationFf0.setTypeface(Typeface.DEFAULT)
                binding.navigationFf2.setTypeface(Typeface.DEFAULT)
                binding.navigationFf1.setTypeface(Typeface.DEFAULT)
                hideAndShow(3, fragmentTran)
            }
        }
        binding.navigationFf4.setOnClickListener {
            activity?.let {
                binding.navigationFf4.setTextColor(Color.parseColor("#FF5500"))
                binding.navigationFf4.setTypeface(Typeface.DEFAULT_BOLD)
                val fragmentTran = it.supportFragmentManager.beginTransaction()
                if (fragments[4] == null) {
                    fragments[4] = Fragment4.newInstance()
                    fragments[4]?.let { fragmentTran.add(R.id.order_content, it, "Fragment4") }
                }
                binding.navigationFf0.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf2.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf1.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf3.setTextColor(Color.parseColor("#000000"))
                binding.navigationFf3.setTypeface(Typeface.DEFAULT)
                binding.navigationFf0.setTypeface(Typeface.DEFAULT)
                binding.navigationFf2.setTypeface(Typeface.DEFAULT)
                binding.navigationFf1.setTypeface(Typeface.DEFAULT)
                hideAndShow(4, fragmentTran)
            }
        }
        testGertOrderList();
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun testGertOrderList(){
        val apiService = ServiceCreator.create(ApiService::class.java)
        val oc = OrderCondition()
        oc.shopId = 12
        apiService.getOrderList(oc).enqueue(object : Callback<ApiResponse<List<OrderVo>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<OrderVo>>>,
                response: Response<ApiResponse<List<OrderVo>>>
            ) {
                val apiResponse = response.body() as ApiResponse<List<OrderVo>>
                if (apiResponse.success){
                    apiResponse.data?.let {
                        //更新ui

                        Log.d(javaClass.simpleName, it.toString())
                    }
                } else {
                    Log.d(javaClass.simpleName,apiResponse.message)
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<OrderVo>>>, t: Throwable) {
                Log.e(javaClass.simpleName,"获取订单失败")
                Log.e(javaClass.simpleName,t.stackTraceToString())
            }


        })

    }


    private fun initFragment(){
        activity?.let {
            val fragmentTran = it.supportFragmentManager.beginTransaction()
            if (fragments[0]==null){
                fragments[0] = Fragment0.newInstance()
                fragments[0]?.let { fragmentTran.add(R.id.order_content, it,"Fragment0").commit() }
            } else {
                fragments[0]?.let { fragmentTran.show(it) }
            }
        }
    }

    private fun hideAndShow(expectIndex: Int, transaction: FragmentTransaction){
        for (i in 0 until fragments.size) {
            if (i != expectIndex && fragments[i] != null) {
                fragments[i]?.let { transaction.hide(it) }
            }
        }
        fragments[expectIndex]?.let { transaction.show(it) }
        transaction.commit()
        currentIndex = expectIndex
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() =
            OrderFragment().apply {

            }
    }

}