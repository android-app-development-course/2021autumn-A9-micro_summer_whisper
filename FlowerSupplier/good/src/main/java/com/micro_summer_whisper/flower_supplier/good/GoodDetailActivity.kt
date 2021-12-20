package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Good
import com.micro_summer_whisper.flower_supplier.common.pojo.GoodDetailNormalItem
import com.micro_summer_whisper.flower_supplier.common.util.PictureUtils
import com.micro_summer_whisper.flower_supplier.good.databinding.ActivityGoodDetailBinding
import kotlin.concurrent.thread


class GoodDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityGoodDetailBinding

    private val picBitmapList = ArrayList<Bitmap>()
    private lateinit var goodDetailPictureAdapter: GoodDetailPictureAdapter
    private var isUpdate = false
    private lateinit var good: Good
    private val goodDetailList = ArrayList<GoodDetailNormalItem>()
    private lateinit var goodDetailNormalAdapter: GoodDetailNormalAdapter

    companion object {
        const val COVER_REQUEST_CODE = 1
        const val SHOW_PICTURE_REQUEST_CODE = 2
        fun actionStart(context: Context, isUpdate: Boolean, good: Good?){
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
        if (isUpdate){
            good = intent.getSerializableExtra("good") as Good
            Glide.with(this).load(good.coverUri).into(binding.goodDetailCoverImage)
        } else {
            binding.goodDetailCoverImage.setImageResource(R.drawable.add)
        }
        initPicLinkList()
        goodDetailPictureAdapter = GoodDetailPictureAdapter(this ,picBitmapList)
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

        binding.goodDetailCoverClose.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, COVER_REQUEST_CODE)
        }

        binding.goodDetailUpdateGoodBtn.setOnClickListener{
            val apiService = ServiceCreator.create(ApiService::class.java)
            val pL = arrayListOf<String>()
            for (bitmap in picBitmapList) {
                pL.add(PictureUtils.bitmap2String(bitmap,100))
            }
            val ss = goodDetailList
            if (isUpdate) {
                apiService.updateGood(Good(good.goodId, FlowerSupplierApplication.store.storeId,
                    PictureUtils.bitmap2String(binding.goodDetailCoverImage.drawToBitmap(),100),
                    pL, goodDetailList[0].content, goodDetailList[1].content.toDouble(), goodDetailList[2].content.toLong(),
                    goodDetailList[3].content
                    ))
            } else {
                apiService.newGood(Good(-1, FlowerSupplierApplication.store.storeId,
                    PictureUtils.bitmap2String(binding.goodDetailCoverImage.drawToBitmap(),100),
                    pL, goodDetailList[0].content, goodDetailList[1].content.toDouble(), goodDetailList[2].content.toLong(),
                    goodDetailList[3].content
                ))
            }
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            COVER_REQUEST_CODE -> if (resultCode === RESULT_OK) { //resultcode是setResult里面设置的code值
                data?.data?.let {  uri ->
                    val bitmap = getBitmapFormUri(uri)
                    binding.goodDetailCoverImage.setImageBitmap(bitmap)
                }
            }
            in SHOW_PICTURE_REQUEST_CODE*10 until SHOW_PICTURE_REQUEST_CODE*10+10 -> if (resultCode== RESULT_OK){
                data?.data?.let {  uri ->
                    val bitmap = getBitmapFormUri(uri)
                    val intExtra = requestCode-SHOW_PICTURE_REQUEST_CODE*10
                    intExtra?.let {
                        if (bitmap!=null){
                            picBitmapList[it] = bitmap
                        }
                        goodDetailPictureAdapter.notifyItemChanged(it)
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }


    private fun initPicLinkList(){
        picBitmapList.clear()
        thread {
            if (isUpdate){
                for (i in 0 until good.pictureUriList.size) {
                    val myBitmap: Bitmap = Glide.with(this)
                        .asBitmap()
                        .load(good.pictureUriList[i])
                        .submit(100, 100).get()
                    picBitmapList.add(myBitmap)
                }
                for (i in good.pictureUriList.size until 6){

                    val myBitmap: Bitmap = Glide.with(this)
                        .asBitmap()
                        .load(R.drawable.add)
                        .submit(100, 100).get()
                    picBitmapList.add(myBitmap)
                }
            } else {
                repeat(6) {
                    val myBitmap: Bitmap = Glide.with(this)
                        .asBitmap()
                        .load(R.drawable.add)
                        .submit(100, 100).get()
                    picBitmapList.add(myBitmap)
                }
            }
        }
    }

    private fun initGoodDetailList(){
        goodDetailList.clear()
        if (isUpdate){
            goodDetailList.add(GoodDetailNormalItem("标题", good.title,60))
            goodDetailList.add(GoodDetailNormalItem("价格",good.price.toString(),60))
            goodDetailList.add(GoodDetailNormalItem("数量",good.stock.toString(),60))
            goodDetailList.add(GoodDetailNormalItem("详情",good.detail,300))
        } else {
            goodDetailList.add(GoodDetailNormalItem("标题","",60))
            goodDetailList.add(GoodDetailNormalItem("价格","",60))
            goodDetailList.add(GoodDetailNormalItem("数量","",60))
            goodDetailList.add(GoodDetailNormalItem("详情","",300))

        }
    }
}