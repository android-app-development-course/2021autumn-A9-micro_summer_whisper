package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Good
import com.micro_summer_whisper.flower_supplier.good.databinding.ActivityGoodSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class GoodSearchActivity : BaseActivity() {

    private lateinit var binding: ActivityGoodSearchBinding
    private val goodList = ArrayList<Good>()
    private lateinit var goodAdapter: GoodAdapter
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.good_search_search -> {
                goodList.clear()
                apiService.getGoodList("all",searchInput.text.toString()).enqueue(object : Callback<List<Good>> {
                    override fun onResponse(
                        call: Call<List<Good>>,
                        response: Response<List<Good>>
                    ) {
                        if (FlowerSupplierApplication.isDebug) {
                            onFailure(call,RuntimeException())
                        } else {
                            val gL = response.body()
                            gL?.let { goodList.addAll(it) }
                        }
                        goodAdapter.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<List<Good>>, t: Throwable) {
                        if (FlowerSupplierApplication.isDebug){
                            goodList.add(Good(2,FlowerSupplierApplication.store.storeId,"https://img10.360buyimg.com/n2/s308x308_jfs/t1/181148/15/4427/297704/60a120cbEf45c2d76/34b8d0f054a06a04.jpg!q70.dpg",
                                arrayListOf("https://img0.baidu.com/it/u=3762399871,962966599&fm=26&fmt=auto","https://img2.baidu.com/it/u=3516865109,3643936755&fm=26&fmt=auto"),
                                "挚爱此生99朵红玫瑰生日花束鲜花速递同城配送表白求婚礼物全国北京上海广州深圳成都重庆东莞沈阳长沙花店 99朵红玫瑰挚爱款",388.00, 100, "detail"
                            ))
                            goodList.add(Good(3,FlowerSupplierApplication.store.storeId,"https://img10.360buyimg.com/n2/s240x240_jfs/t19888/49/2439091503/72253/4f34c02f/5b41b386Na3776807.jpg!q70.jpg",
                                arrayListOf("https://img10.360buyimg.com/n2/s240x240_jfs/t19888/49/2439091503/72253/4f34c02f/5b41b386Na3776807.jpg!q70.jpg"),
                                "刘阁 春兰墨兰建兰花苗大集合 香妃 有香味的兰花 夏带花苞出售室内外花卉绿植盆栽草观花植物 香妃2苗加盆土肥，说明书",16.00, 100, "detail"
                            ))
                        } else {
                            Log.e(javaClass.simpleName,"获取搜索结果失败")
                            Log.e(javaClass.simpleName,t.stackTraceToString())
                        }
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