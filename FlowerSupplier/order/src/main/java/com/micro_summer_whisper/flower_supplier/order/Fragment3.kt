package com.micro_summer_whisper.flower_supplier.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.order10.Order
import com.example.order10.OrderAdapter
import com.micro_summer_whisper.flower_supplier.order.databinding.Fragment1Binding
import com.micro_summer_whisper.flower_supplier.order.databinding.Fragment3Binding


class Fragment3 : Fragment() {
    private var _binding: Fragment3Binding? = null
    private val binding get() = _binding!!
    private val orderList = ArrayList<Order>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initOrders()

        val filterOrderList = orderList.filter {
            it.statu == 3
        }

        _binding = Fragment3Binding.inflate(inflater, container, false)
        val view = binding.root
        val layoutManager = LinearLayoutManager(this.context)
        binding.orderRecyclerview3.layoutManager = layoutManager
        val adapter = OrderAdapter(filterOrderList)
        binding.orderRecyclerview3.adapter = adapter
        return view

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Fragment3().apply {

            }
    }

    private fun initOrders() {
        orderList.add(Order("洋甘菊", (0..4).random(), "1.23", "3", R.drawable.f1))
        orderList.add(Order("玫瑰", (0..4).random(), "1.23", "3", R.drawable.f2))
        orderList.add(Order("鸡冠花", (0..4).random(), "1.23", "3", R.drawable.f3))
        orderList.add(Order("牡丹", (0..4).random(), "1.23", "3", R.drawable.f4))
        orderList.add(Order("康乃馨", (0..4).random(), "1.23", "3", R.drawable.f5))
        orderList.add(Order("菊花", (0..4).random(), "1.23", "3", R.drawable.f6))
        orderList.add(Order("虞美人", (0..4).random(), "1.23", "3", R.drawable.f7))
        orderList.add(Order("山药", (0..4).random(), "1.23", "3", R.drawable.f8))
        orderList.add(Order("荷花", (0..4).random(), "1.23", "3", R.drawable.f9))
        orderList.add(Order("芍药", (0..4).random(), "1.23", "3", R.drawable.f10))
    }
}
