package com.micro_summer_whisper.flower_supplier.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.store.databinding.FragmentStoreBinding
import kotlin.random.Random


class StoreFragment : Fragment() {


    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private val storeDataList = ArrayList<StoreData>()
    private lateinit var storeDataAdapter: StoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root
        Glide.with(this).load("https://gw.alicdn.com/tps/i3/TB1yeWeIFXXXXX5XFXXuAZJYXXX-210-210.png_50x50.jpg").into(binding.storeHeadImage)
        initStoreDataList()
        this.activity?.let {
            storeDataAdapter = StoreAdapter(this ,storeDataList)
            binding.storeRecyclerview.adapter = storeDataAdapter
            val layoutManager1 = LinearLayoutManager(it)
            binding.storeRecyclerview.layoutManager = layoutManager1
        }
        binding.storeHeadImage.setOnClickListener {
            this.context?.let {
                StoreSettingActivity.actionStart(it)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() =
            StoreFragment().apply {

            }
    }

    private fun initStoreDataList(){
        storeDataList.clear()
        val data1 = StoreDataBuilder()
            .setStoreDataType(StoreData.StoreDataType.SIX_TEXT_BOX)
            .setSixTextBoxTitleList(arrayListOf("今日访问量", "昨日访问量", "今日订单", "昨日订单", "未付款", "未评价"))
            .setSixTextBoxContentList(arrayListOf("100", "300", "100", "400", "20", "30"))
            .build()
        val data2 = StoreDataBuilder()
            .setStoreDataType(StoreData.StoreDataType.SIX_TEXT_BOX)
            .setSixTextBoxTitleList(arrayListOf("未确认收货", "     ", "     ", "     ", "", "     "))
            .setSixTextBoxContentList(arrayListOf("100", "", "", "", "", ""))
            .build()
        storeDataList.add(data1)
        storeDataList.add(data2)

    }
}