package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore

import android.view.MenuItem
import androidx.core.view.drawToBitmap
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Good
import com.micro_summer_whisper.flower_supplier.common.util.PictureUtils
import com.micro_summer_whisper.flower_supplier.good.databinding.ActivityGoodDetailBinding


class GoodDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityGoodDetailBinding
    private var isUpdate = false
    private lateinit var good: Good

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
            good = Good(-1,-1,"",ArrayList<String>(),"",0.0,0,"")
            binding.goodDetailCoverImage.setImageResource(R.drawable.add)
        }
        initPicLinkList()
        initGoodDetailList()
        binding.goodDetailCoverImage.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, COVER_REQUEST_CODE)
        }

        binding.goodDetailUpdateGoodBtn.setOnClickListener{
            val apiService = ServiceCreator.create(ApiService::class.java)
            if (isUpdate) {
                apiService.updateGood(Good(good.goodId, FlowerSupplierApplication.store.storeId,
                    PictureUtils.bitmap2String(binding.goodDetailCoverImage.drawToBitmap(),100),
                    arrayListOf(PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage1.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage2.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage3.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage4.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage5.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage6.drawToBitmap(),100)),
                    binding.goodDetailNormalTitleInput.text.toString(),
                    binding.goodDetailNormalPriceInput.text.toString().toDouble(),
                    binding.goodDetailNormalStockInput.text.toString().toLong(),
                    binding.goodDetailNormalDetailInput.text.toString()
                    ))
            } else {
                apiService.newGood(Good(-1, FlowerSupplierApplication.store.storeId,
                    PictureUtils.bitmap2String(binding.goodDetailCoverImage.drawToBitmap(),100),
                    arrayListOf(PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage1.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage2.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage3.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage4.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage5.drawToBitmap(),100),
                        PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage6.drawToBitmap(),100)),
                    binding.goodDetailNormalTitleInput.text.toString(),
                    binding.goodDetailNormalPriceInput.text.toString().toDouble(),
                    binding.goodDetailNormalStockInput.text.toString().toLong(),
                    binding.goodDetailNormalDetailInput.text.toString()
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
                        when(it) {
                            1 -> binding.goodDetailShowpictureItemImage1.setImageBitmap(bitmap)
                            2 ->     binding.goodDetailShowpictureItemImage2.setImageBitmap(bitmap)
                            3 ->     binding.goodDetailShowpictureItemImage3.setImageBitmap(bitmap)
                            4 ->     binding.goodDetailShowpictureItemImage4.setImageBitmap(bitmap)
                            5 ->     binding.goodDetailShowpictureItemImage5.setImageBitmap(bitmap)
                            6 ->     binding.goodDetailShowpictureItemImage6.setImageBitmap(bitmap)
                        }
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
        for (i in 0 until good.pictureUriList.size) {
            when(i+1) {
                1 -> {
                    Glide.with(this).load(good.pictureUriList[i]).into(binding.goodDetailShowpictureItemImage1)
                }
                2 -> {
                    Glide.with(this).load(good.pictureUriList[i]).into(binding.goodDetailShowpictureItemImage2)
                }
                3 -> {
                    Glide.with(this).load(good.pictureUriList[i]).into(binding.goodDetailShowpictureItemImage3)
                }
                4 -> {
                    Glide.with(this).load(good.pictureUriList[i]).into(binding.goodDetailShowpictureItemImage4)
                }
                5 -> {
                    Glide.with(this).load(good.pictureUriList[i]).into(binding.goodDetailShowpictureItemImage5)
                }
                6 -> {
                    Glide.with(this).load(good.pictureUriList[i]).into(binding.goodDetailShowpictureItemImage6)
                }
            }
        }
        for (i in good.pictureUriList.size until 6){
            when(i+1) {
                1 -> {
                    Glide.with(this).load(R.drawable.add).into(binding.goodDetailShowpictureItemImage1)
                }
                2 -> {
                    Glide.with(this).load(    R.drawable.add).into(binding.goodDetailShowpictureItemImage2)
                }
                3 -> {
                    Glide.with(this).load(    R.drawable.add).into(binding.goodDetailShowpictureItemImage3)
                }
                4 -> {
                    Glide.with(this).load(    R.drawable.add).into(binding.goodDetailShowpictureItemImage4)
                }
                5 -> {
                    Glide.with(this).load(    R.drawable.add).into(binding.goodDetailShowpictureItemImage5)
                }
                6 -> {
                    Glide.with(this).load(    R.drawable.add).into(binding.goodDetailShowpictureItemImage6)
                }
            }
        }
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        binding.goodDetailShowpictureItemImage1.setOnClickListener {
            startActivityForResult(intent, SHOW_PICTURE_REQUEST_CODE*10+1)
        }
        binding.goodDetailShowpictureItemImage2.setOnClickListener {
            startActivityForResult(intent, SHOW_PICTURE_REQUEST_CODE*10+2)
        }
        binding.goodDetailShowpictureItemImage3.setOnClickListener {
            startActivityForResult(intent, SHOW_PICTURE_REQUEST_CODE*10+3)
        }
        binding.goodDetailShowpictureItemImage4.setOnClickListener {
            startActivityForResult(intent, SHOW_PICTURE_REQUEST_CODE*10+4)
        }
        binding.goodDetailShowpictureItemImage5.setOnClickListener {
            startActivityForResult(intent, SHOW_PICTURE_REQUEST_CODE*10+5)
        }
        binding.goodDetailShowpictureItemImage6.setOnClickListener {
            startActivityForResult(intent, SHOW_PICTURE_REQUEST_CODE*10+6)
        }
    }

    private fun initGoodDetailList(){
        binding.goodDetailNormalTitleInput.setText(good.title)
        binding.goodDetailNormalPriceInput.setText(good.price.toString())
        binding.goodDetailNormalStockInput.setText(good.stock.toString())
        binding.goodDetailNormalDetailInput.setText(good.detail)
    }
}