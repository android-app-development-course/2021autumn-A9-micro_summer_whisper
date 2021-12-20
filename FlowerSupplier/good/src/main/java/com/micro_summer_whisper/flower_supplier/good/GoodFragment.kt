package com.micro_summer_whisper.flower_supplier.good


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.micro_summer_whisper.flower_supplier.good.databinding.FragmentGoodBinding

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Good
import com.micro_summer_whisper.flower_supplier.common.randomTimes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.random.Random


class GoodFragment : Fragment()
{
    private var _binding: FragmentGoodBinding? = null
    private val binding get() = _binding!!
    private val goodList = ArrayList<Good>()
    private val goodCategoryList = ArrayList<String>()
    private lateinit var goodAdapter: GoodAdapter
    private lateinit var goodCategoryAdapter: GoodCategoryAdapter
    private var curCategoryHolder: GoodCategoryAdapter.GoodCategoryViewHolder? = null
    private val apiService = ServiceCreator.create(ApiService::class.java)

    inner class GoodCategoryAdapterImpl(fragment: Fragment,  goodCategoryList:ArrayList<String>): GoodCategoryAdapter(fragment, goodCategoryList){
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

        initGoodCategoryList()
        activity?.let {
            goodAdapter = GoodAdapter(this ,goodList)
            binding.goodListRecyclerview.adapter = goodAdapter
            val layoutManager = LinearLayoutManager(it)
            binding.goodListRecyclerview.layoutManager = layoutManager

            goodCategoryAdapter = GoodCategoryAdapterImpl(this ,goodCategoryList)
            binding.goodCategoryRecyclerview.adapter = goodCategoryAdapter
            val layoutManager1 = LinearLayoutManager(it)
            binding.goodCategoryRecyclerview.layoutManager = layoutManager1

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

    private fun initGoodList(category: String){
        goodList.clear()
        apiService.getGoodList(category).enqueue(object : Callback<List<Good>>{
            override fun onResponse(call: Call<List<Good>>, response: Response<List<Good>>) {
                if (FlowerSupplierApplication.isDebug) {
                    onFailure(call, RuntimeException())
                } else {
                    val gL = response.body()
                    gL?.let { goodList.addAll(it) }
                }
                goodAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Good>>, t: Throwable) {
                if (FlowerSupplierApplication.isDebug) {
                    when(category) {
                        "全部" -> {
                            goodList.add(Good(1,FlowerSupplierApplication.store.storeId,"https://img12.360buyimg.com/n2/s370x370_jfs/t1/134051/31/3428/586104/5efd2f06E16eebdea/74569d2b84ed48ee.jpg!q70.jpg",
                                arrayListOf("https://img0.baidu.com/it/u=3762399871,962966599&fm=26&fmt=auto","https://img2.baidu.com/it/u=3516865109,3643936755&fm=26&fmt=auto"),
                                " I'M HUAHUA 51朵红玫瑰花满天星花束礼盒香皂玫瑰花礼物圣诞节礼物鲜情人节生日礼物表白送老婆送女友",98.00, 100, "detail"
                            ))
                            goodList.add(Good(2,FlowerSupplierApplication.store.storeId,"https://img10.360buyimg.com/n2/s308x308_jfs/t1/181148/15/4427/297704/60a120cbEf45c2d76/34b8d0f054a06a04.jpg!q70.dpg",
                                arrayListOf("https://img0.baidu.com/it/u=3762399871,962966599&fm=26&fmt=auto","https://img2.baidu.com/it/u=3516865109,3643936755&fm=26&fmt=auto"),
                                "挚爱此生99朵红玫瑰生日花束鲜花速递同城配送表白求婚礼物全国北京上海广州深圳成都重庆东莞沈阳长沙花店 99朵红玫瑰挚爱款",388.00, 100, "detail"
                            ))
                            goodList.add(Good(3,FlowerSupplierApplication.store.storeId,"https://img10.360buyimg.com/n2/s240x240_jfs/t19888/49/2439091503/72253/4f34c02f/5b41b386Na3776807.jpg!q70.jpg",
                                arrayListOf("https://img10.360buyimg.com/n2/s240x240_jfs/t19888/49/2439091503/72253/4f34c02f/5b41b386Na3776807.jpg!q70.jpg"),
                                "刘阁 春兰墨兰建兰花苗大集合 香妃 有香味的兰花 夏带花苞出售室内外花卉绿植盆栽草观花植物 香妃2苗加盆土肥，说明书",16.00, 100, "detail"
                            ))
                            goodList.add(Good(4,FlowerSupplierApplication.store.storeId,"https://img11.360buyimg.com/n2/s240x240_jfs/t1/161962/16/16158/126076/6067af4bEc8f3ca7c/c24ff6cac1a452a9.jpg!q70.jpg",
                                arrayListOf("https://img11.360buyimg.com/n2/s240x240_jfs/t1/161962/16/16158/126076/6067af4bEc8f3ca7c/c24ff6cac1a452a9.jpg!q70.jpg"),
                                "新店开张 超香兰花 兰花苗 室内绿植花卉好养 蓝宝石 5苗连体 99朵红玫瑰挚爱款",19.00, 100, "detail"
                            ))
                        }
                        "玫瑰" -> {
                            goodList.add(Good(1,FlowerSupplierApplication.store.storeId,"https://img12.360buyimg.com/n2/s370x370_jfs/t1/134051/31/3428/586104/5efd2f06E16eebdea/74569d2b84ed48ee.jpg!q70.jpg",
                                arrayListOf("https://img0.baidu.com/it/u=3762399871,962966599&fm=26&fmt=auto","https://img2.baidu.com/it/u=3516865109,3643936755&fm=26&fmt=auto"),
                                " I'M HUAHUA 51朵红玫瑰花满天星花束礼盒香皂玫瑰花礼物圣诞节礼物鲜情人节生日礼物表白送老婆送女友",98.00, 100, "detail"
                            ))
                            goodList.add(Good(2,FlowerSupplierApplication.store.storeId,"https://img10.360buyimg.com/n2/s308x308_jfs/t1/181148/15/4427/297704/60a120cbEf45c2d76/34b8d0f054a06a04.jpg!q70.dpg",
                                arrayListOf("https://img0.baidu.com/it/u=3762399871,962966599&fm=26&fmt=auto","https://img2.baidu.com/it/u=3516865109,3643936755&fm=26&fmt=auto"),
                                "挚爱此生99朵红玫瑰生日花束鲜花速递同城配送表白求婚礼物全国北京上海广州深圳成都重庆东莞沈阳长沙花店 99朵红玫瑰挚爱款",388.00, 100, "detail"
                            ))
                        }
                        "兰花" -> {
                            goodList.add(Good(3,FlowerSupplierApplication.store.storeId,"https://img10.360buyimg.com/n2/s240x240_jfs/t19888/49/2439091503/72253/4f34c02f/5b41b386Na3776807.jpg!q70.jpg",
                                arrayListOf("https://img10.360buyimg.com/n2/s240x240_jfs/t19888/49/2439091503/72253/4f34c02f/5b41b386Na3776807.jpg!q70.jpg"),
                                "刘阁 春兰墨兰建兰花苗大集合 香妃 有香味的兰花 夏带花苞出售室内外花卉绿植盆栽草观花植物 香妃2苗加盆土肥，说明书",16.00, 100, "detail"
                            ))
                            goodList.add(Good(4,FlowerSupplierApplication.store.storeId,"https://img11.360buyimg.com/n2/s240x240_jfs/t1/161962/16/16158/126076/6067af4bEc8f3ca7c/c24ff6cac1a452a9.jpg!q70.jpg",
                                arrayListOf("https://img11.360buyimg.com/n2/s240x240_jfs/t1/161962/16/16158/126076/6067af4bEc8f3ca7c/c24ff6cac1a452a9.jpg!q70.jpg"),
                                "新店开张 超香兰花 兰花苗 室内绿植花卉好养 蓝宝石 5苗连体 99朵红玫瑰挚爱款",19.00, 100, "detail"
                            ))
                        }
                    }
                } else {
                    Log.e(javaClass.simpleName,"获取商品列表失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                }

            }

        })
    }

    private fun initGoodCategoryList(){
        goodCategoryList.clear()
        apiService.getGoodCategoryList().enqueue(object : Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (FlowerSupplierApplication.isDebug){
                    onFailure(call,RuntimeException())
                } else {
                    val categoryL = response.body()
                    categoryL?.let { goodCategoryList.addAll(it) }
                }
                goodCategoryAdapter.notifyDataSetChanged()
                initGoodList(goodCategoryList[0])
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                if (FlowerSupplierApplication.isDebug){
                    goodCategoryList.add("全部")
                    goodCategoryList.add("玫瑰")
                    goodCategoryList.add("兰花")
                } else {
                    Log.e(javaClass.simpleName,"获取商品分类失败")
                    Log.e(javaClass.simpleName,t.stackTraceToString())
                }
            }

        })
    }
}