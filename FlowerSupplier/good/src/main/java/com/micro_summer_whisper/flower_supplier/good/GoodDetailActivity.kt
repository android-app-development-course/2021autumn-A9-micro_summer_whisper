package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.Good
import com.micro_summer_whisper.flower_supplier.common.randomTimes
import com.micro_summer_whisper.flower_supplier.good.databinding.ActivityGoodDetailBinding
import com.micro_summer_whisper.flower_supplier.good.databinding.ActivityMainBinding
import kotlin.random.Random

class GoodDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityGoodDetailBinding

    private val picLinkList = ArrayList<String>()
    private lateinit var goodDetailPictureAdapter: GoodDetailPictureAdapter
    private var isUpdate = false
    private lateinit var good: Good
    private val goodDetailList = ArrayList<Pair<String,String>>()
    private lateinit var goodDetailNormalAdapter: GoodDetailNormalAdapter

    companion object {
        fun actionStart(context: Context, isUpdate: Boolean, good: Good){
            val intent = Intent(context,GoodDetailActivity::class.java)
            intent.putExtra("isUpdate",isUpdate)
            intent.putExtra("good",good)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.goodDetailToolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(true)
            it.setTitle("编辑商品")
        }
        isUpdate = intent.getBooleanExtra("isUpdate",false)
        good = intent.getSerializableExtra("good") as Good
        if (isUpdate) {
            initPicLinkList1()
        } else {
            initPicLinkList0()
        }
        goodDetailPictureAdapter = GoodDetailPictureAdapter(this ,picLinkList)
        binding.goodDetailShowpictureRecyclerview.adapter = goodDetailPictureAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.goodDetailShowpictureRecyclerview.layoutManager = layoutManager

        initGoodDetailList()
        goodDetailNormalAdapter = GoodDetailNormalAdapter(this ,goodDetailList)
        binding.goodDetailRecyclerview.adapter = goodDetailNormalAdapter
        val layoutManager1 = LinearLayoutManager(this)
        layoutManager1.orientation = LinearLayoutManager.VERTICAL
        binding.goodDetailRecyclerview.layoutManager = layoutManager1

    }


    private fun initPicLinkList1(){
        picLinkList.clear()
        repeat(3) {
            picLinkList.add("https://img.alicdn.com/bao/uploaded/i4/36705187/TB2xttsbjgy_uJjSZR0XXaK5pXa_!!36705187.jpg_200x200q90.jpg_.webp")
        }
        repeat(3){
            picLinkList.add("")
        }
    }

    private fun initPicLinkList0(){
        picLinkList.clear()
        repeat(6){
            picLinkList.add("")
        }
    }

    private fun initGoodDetailList(){
        goodDetailList.clear()
        repeat(12){
            goodDetailList.add(Pair("标题${Random.nextInt(10)}","${Random.nextInt(10)}".randomTimes(5)))
        }
    }
}