package com.micro_summer_whisper.flower_supplier.good


import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.micro_summer_whisper.flower_supplier.good.databinding.FragmentGoodBinding

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.conditon.ProductCondition
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.CategoryVo
import com.micro_summer_whisper.flower_supplier.common.pojo.ProductVo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GoodFragment : Fragment()
{
    private var _binding: FragmentGoodBinding? = null
    private val binding get() = _binding!!
    private val goodList = ArrayList<ProductVo>()
    private val goodCategoryList = ArrayList<CategoryVo>()
    private lateinit var goodAdapter: GoodAdapter
    private lateinit var goodCategoryAdapter: GoodCategoryAdapter
    private var curCategoryHolder: GoodCategoryAdapter.GoodCategoryViewHolder? = null
    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    inner class GoodCategoryAdapterImpl(fragment: Fragment,  goodCategoryList:ArrayList<CategoryVo>): GoodCategoryAdapter(fragment, goodCategoryList){
        @RequiresApi(Build.VERSION_CODES.O)
        override fun nameOnClickListener(holder: GoodCategoryViewHolder): View.OnClickListener {
            return View.OnClickListener {
                curCategoryHolder?.nameTV?.setTextColor(Color.parseColor("#000000"))
                curCategoryHolder = holder
                holder.nameTV.setTextColor(Color.parseColor("#0000ff"))
                initGoodList(holder.nameTV.text.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoodBinding.inflate(inflater, container, false)
        val view = binding.root


        activity?.let {
            this.context?.let {
                goodAdapter = GoodAdapter(it ,goodList)
                binding.goodListRecyclerview.adapter = goodAdapter
                val layoutManager = LinearLayoutManager(it)
                binding.goodListRecyclerview.layoutManager = layoutManager

                goodCategoryAdapter = GoodCategoryAdapterImpl(this ,goodCategoryList)
                binding.goodCategoryRecyclerview.adapter = goodCategoryAdapter
                val layoutManager1 = LinearLayoutManager(it)
                binding.goodCategoryRecyclerview.layoutManager = layoutManager1

            }
        }

        binding.goodAddBtn.setOnClickListener {
            this.context?.let {
                GoodDetailActivity.actionStart(it,false,null)
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        val appCompatActivity = activity as AppCompatActivity?
        val toolbar: Toolbar = appCompatActivity!!.findViewById<View>(R.id.good_toolbar) as Toolbar
        appCompatActivity?.let {
            it.setSupportActionBar(toolbar)
            it.supportActionBar?.setDisplayShowTitleEnabled(false)
        }
        super.onActivityCreated(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        initGoodCategoryList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.good_toolbar,menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.good_search -> {
                this.context?.let {
                    GoodSearchActivity.actionStart(it)
                }
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() =
            GoodFragment().apply {

            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initGoodList(category: String){
        goodList.clear()
        val proCondition = ProductCondition()
        proCondition.categoryName = category
        apiService.getGoodList(proCondition).enqueue(object : Callback<ApiResponse<List<ProductVo>>>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ApiResponse<List<ProductVo>>>, response: Response<ApiResponse<List<ProductVo>>>) {
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
                    Log.e(javaClass.simpleName,"获取商品列表失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initGoodCategoryList(){
        goodCategoryList.clear()
        goodCategoryList.add(CategoryVo(null,"全部",null,null,null,null,null,null))
        apiService.getGoodCategoryList().enqueue(object : Callback<ApiResponse<List<CategoryVo>>>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ApiResponse<List<CategoryVo>>>, response: Response<ApiResponse<List<CategoryVo>>>) {
                val apiResponse = response.body() as ApiResponse<List<CategoryVo>>
                if (apiResponse.success){
                    apiResponse.data?.let {
                        goodCategoryList.addAll(it)
                        goodCategoryAdapter.notifyDataSetChanged()
                        initGoodList(goodCategoryList[0].categoryName)
                    }
                } else {
                    Log.d(javaClass.simpleName,apiResponse.message)
                }

            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFailure(call: Call<ApiResponse<List<CategoryVo>>>, t: Throwable) {
                    Log.e(javaClass.simpleName,"获取商品分类失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
            }

        })
    }
}