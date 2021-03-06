package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log

import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.view.drawToBitmap
import cn.hutool.core.math.Money
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.ProductVo
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.common.util.PictureUtils
import com.micro_summer_whisper.flower_supplier.good.databinding.ActivityGoodDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime


class GoodDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityGoodDetailBinding
    private var isUpdate = false
    private lateinit var good: ProductVo
    private val pictureUpdatedArr = arrayOf(false,false,false,false,false,false,false)

    companion object {
        const val COVER_REQUEST_CODE = 1
        const val SHOW_PICTURE_REQUEST_CODE = 2
        fun actionStart(context: Context, isUpdate: Boolean, good: ProductVo?){
            val intent = Intent(context,GoodDetailActivity::class.java)
            intent.putExtra("isUpdate",isUpdate)
            intent.putExtra("good",good)
            context.startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.goodDetailToolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(true)
            it.setTitle("????????????")
        }
        isUpdate = intent.getBooleanExtra("isUpdate",false)
        if (isUpdate){
            good = intent.getSerializableExtra("good") as ProductVo
            Glide.with(this).load(good.imgAddr).into(binding.goodDetailCoverImage)
        } else {
            good = ProductVo()
            good.createTime = LocalDateTime.now()
            good.lastEditTime = LocalDateTime.now()
            good.normalPrice = 0
            good.productName = ""
            good.categoryName = ""
            good.productDesc = ""
            good.stock = 0
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
                val pv = ProductVo()
                pv.productId = good.productId
                pv.shopId = FlowerSupplierApplication.store.shopId
                pv.categoryName = binding.goodDetailNormalCategoryInput.text.toString()
                pv.productName = binding.goodDetailNormalTitleInput.text.toString()
                pv.productDesc = binding.goodDetailNormalDetailInput.text.toString()
                pv.stock = binding.goodDetailNormalStockInput.text.toString().toInt()
                pv.normalPrice = Money(binding.goodDetailNormalPriceInput.text.toString()).cent.toInt()
                if(pictureUpdatedArr[0]){
                    pv.imgAddr = PictureUtils.bitmap2String(binding.goodDetailCoverImage.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[1]){
                    pv.pictureA = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage1.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[2]){
                    pv.pictureB = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage2.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[3]){
                    pv.pictureC = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage3.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[4]){
                    pv.pictureD = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage4.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[5]){
                    pv.pictureE = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage5.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[6]){
                    pv.pictureF = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage6.drawToBitmap(),100)
                }
                apiService.saveOrUpdateGood(pv).enqueue(object : Callback<ApiResponse<ProductVo>>{
                    override fun onResponse(
                        call: Call<ApiResponse<ProductVo>>,
                        response: Response<ApiResponse<ProductVo>>
                    ) {

                        val apiResponse = response.body() as ApiResponse<ProductVo>
                        if (apiResponse.success){
                            apiResponse.data?.let {
                                Log.d(javaClass.simpleName,"?????????????????? ${it.toString()}")
                                "??????????????????".shortToast()
                            }
                        } else {
                            Log.d(javaClass.simpleName,apiResponse.message)
                        }

                    }

                    override fun onFailure(call: Call<ApiResponse<ProductVo>>, t: Throwable) {
                        "??????????????????".shortToast()
                        Log.e(javaClass.simpleName,t.stackTraceToString())
                    }

                })

            } else {
                val pv = ProductVo()
                pv.categoryName = binding.goodDetailNormalCategoryInput.text.toString()
                pv.productName = binding.goodDetailNormalTitleInput.text.toString()
                pv.shopId = FlowerSupplierApplication.store.shopId
                pv.productDesc = binding.goodDetailNormalDetailInput.text.toString()
                pv.shopId = FlowerSupplierApplication.store.shopId
                pv.stock = binding.goodDetailNormalStockInput.text.toString().toInt()
                pv.normalPrice = Money(binding.goodDetailNormalPriceInput.text.toString()).cent.toInt()
                if(pictureUpdatedArr[0]){
                    pv.imgAddr = PictureUtils.bitmap2String(binding.goodDetailCoverImage.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[1]){
                    pv.pictureA = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage1.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[2]){
                    pv.pictureB = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage2.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[3]){
                    pv.pictureC = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage3.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[4]){
                    pv.pictureD = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage4.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[5]){
                    pv.pictureE = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage5.drawToBitmap(),100)
                }
                if (pictureUpdatedArr[6]){
                    pv.pictureF = PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage6.drawToBitmap(),100)
                }
                apiService.saveOrUpdateGood(pv).enqueue(object : Callback<ApiResponse<ProductVo>>{
                    override fun onResponse(
                        call: Call<ApiResponse<ProductVo>>,
                        response: Response<ApiResponse<ProductVo>>
                    ) {

                        val apiResponse = response.body() as ApiResponse<ProductVo>
                        if (apiResponse.success) {
                            apiResponse.data?.let {
                                Log.d(javaClass.simpleName,"?????????????????? ${it.toString()}")
                                "??????????????????".shortToast()
                            }

                        } else {
                            Log.d(javaClass.simpleName,apiResponse.message)
                        }

                    }

                    override fun onFailure(call: Call<ApiResponse<ProductVo>>, t: Throwable) {
                        "??????????????????".shortToast()
                        Log.e(javaClass.simpleName,t.stackTraceToString())
                    }

                })
            }
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            COVER_REQUEST_CODE -> if (resultCode === RESULT_OK) { //resultcode???setResult???????????????code???
                data?.data?.let {  uri ->
                    val bitmap = getBitmapFormUri(uri)
                    binding.goodDetailCoverImage.setImageBitmap(bitmap)
                    pictureUpdatedArr[0] = true
                }
            }
            in SHOW_PICTURE_REQUEST_CODE*10 until SHOW_PICTURE_REQUEST_CODE*10+10 -> if (resultCode== RESULT_OK){
                data?.data?.let {  uri ->
                    val bitmap = getBitmapFormUri(uri)
                    val intExtra = requestCode-SHOW_PICTURE_REQUEST_CODE*10
                    intExtra?.let {
                        when(it) {
                            1 -> {
                                binding.goodDetailShowpictureItemImage1.setImageBitmap(bitmap)
                                pictureUpdatedArr[1] = true
                            }
                            2 ->     {
                                binding.goodDetailShowpictureItemImage2.setImageBitmap(bitmap)
                                pictureUpdatedArr[2] = true
                            }
                            3 ->     {
                                binding.goodDetailShowpictureItemImage3.setImageBitmap(bitmap)
                                pictureUpdatedArr[3] = true
                            }
                            4 ->    {
                                binding.goodDetailShowpictureItemImage4.setImageBitmap(bitmap)
                                pictureUpdatedArr[4] = true
                            }
                            5 ->     {
                                binding.goodDetailShowpictureItemImage5.setImageBitmap(bitmap)
                                pictureUpdatedArr[5] = true
                            }
                            6 ->     {
                                binding.goodDetailShowpictureItemImage6.setImageBitmap(bitmap)
                                pictureUpdatedArr[6] = true
                            }
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
        if (good.pictureA!=null&&!"".equals(good.pictureA)) {
            Glide.with(this).load(good.pictureA).into(binding.goodDetailShowpictureItemImage1)
        }
        if (good.pictureB!=null&&!"".equals(good.pictureB)) {
            Glide.with(this).load(good.pictureB).into(binding.goodDetailShowpictureItemImage2)
        }
        if (good.pictureC!=null&&!"".equals(good.pictureC)) {
            Glide.with(this).load(good.pictureC).into(binding.goodDetailShowpictureItemImage3)
        }
        if (good.pictureD!=null&&!"".equals(good.pictureD)) {
            Glide.with(this).load(good.pictureD).into(binding.goodDetailShowpictureItemImage4)
        }
        if (good.pictureE!=null&&!"".equals(good.pictureE)) {
            Glide.with(this).load(good.pictureE).into(binding.goodDetailShowpictureItemImage5)
        }
        if (good.pictureF!=null&&!"".equals(good.pictureF)) {
            Glide.with(this).load(good.pictureF).into(binding.goodDetailShowpictureItemImage6)
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
        binding.goodDetailNormalTitleInput.setText(good.productName)
        binding.goodDetailNormalPriceInput.setText(Money((good.normalPrice/100).toLong(),good.normalPrice%100).toString())
        binding.goodDetailNormalStockInput.setText(good.stock.toString())
        binding.goodDetailNormalDetailInput.setText(good.productDesc)
        binding.goodDetailNormalCategoryInput.setText(good.categoryName)
    }
}