package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.conditon.ProductCondition
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.ProductVo
import com.micro_summer_whisper.flower_supplier.good.databinding.ActivityGoodSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class GoodSearchActivity : BaseActivity() {

    private lateinit var binding: ActivityGoodSearchBinding
    private val goodList = ArrayList<ProductVo>()
    private lateinit var goodAdapter: GoodAdapter
    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)
    private lateinit var searchInput: EditText

    companion object {
        fun actionStart(context: Context){
            val intent = Intent(context,GoodSearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoodSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.goodSearchToolbar)
        val searchView = layoutInflater.inflate(R.layout.good_search_input, null, false)
        searchInput = searchView.findViewById<EditText>(R.id.good_search_input)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayShowCustomEnabled(true)
            it.setCustomView(searchView)
        }
        goodAdapter = GoodAdapter(this,goodList)
        binding.goodSearchRecycleview.adapter = goodAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.goodSearchRecycleview.layoutManager = layoutManager
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.good_search_search -> {
                goodList.clear()
                val proCondition = ProductCondition()
                proCondition.productName = searchInput.text.toString()
                proCondition.shopId = FlowerSupplierApplication.store.shopId
                apiService.getGoodList(proCondition).enqueue(object : Callback<ApiResponse<List<ProductVo>>> {
                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onResponse(
                        call: Call<ApiResponse<List<ProductVo>>>,
                        response: Response<ApiResponse<List<ProductVo>>>
                    ) {
                        val apiResponse = response.body() as ApiResponse<List<ProductVo>>
                        if (apiResponse.success){
                            apiResponse.data?.let {
                                goodList.addAll(it)
                            }
                            goodAdapter.notifyDataSetChanged()
                        } else {
                            Log.d(javaClass.simpleName,apiResponse.message)
                        }
                    }

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onFailure(call: Call<ApiResponse<List<ProductVo>>>, t: Throwable) {

                            Log.e(javaClass.simpleName,"获取搜索结果失败")
                            Log.e(javaClass.simpleName,t.stackTraceToString())

                    }

                })
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.good_search_toolbar,menu)
        return true
    }

}