package com.micro_summer_whisper.flower_supplier.good


import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.micro_summer_whisper.flower_supplier.good.databinding.FragmentGoodBinding

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.Good
import com.micro_summer_whisper.flower_supplier.common.randomTimes
import kotlin.random.Random


class GoodFragment : Fragment()
{
    private var _binding: FragmentGoodBinding? = null
    private val binding get() = _binding!!
    private val goodList = ArrayList<Good>()
    private val goodCategoryList = ArrayList<String>()
    private lateinit var goodAdapter: GoodAdapter
    private lateinit var goodCategoryAdapter: GoodCategoryAdapter

    inner class GoodCategoryAdapterImpl(fragment: Fragment,  goodCategoryList:ArrayList<String>): GoodCategoryAdapter(fragment, goodCategoryList){
        override fun nameOnClickListener(holder: GoodCategoryViewHolder): View.OnClickListener {
            return View.OnClickListener {
                initGoodList()
                goodAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoodBinding.inflate(inflater, container, false)
        val view = binding.root

        initGoodList()
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

    private fun initGoodList(){
        goodList.clear()
        repeat(50) {
            goodList.add(Good(1,1, "https://cdn2.jianshu.io/assets/default_avatar/12-aeeea4bedf10f2a12c0d50d626951489.jpg",
                ArrayList(), "title ${Random.nextInt(10)}", Random.nextDouble(100.0),100, "detail ${Random.nextInt(20)}".randomTimes(20)
                ))
        }
    }

    private fun initGoodCategoryList(){
        goodCategoryList.clear()
        repeat(50) {
            goodCategoryList.add("分类 ${Random.nextInt(10)}")
        }
    }
}