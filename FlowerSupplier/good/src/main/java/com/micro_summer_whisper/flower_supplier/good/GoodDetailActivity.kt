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
            it.setTitle("编辑商品")
        }
        isUpdate = intent.getBooleanExtra("isUpdate",false)
        if (isUpdate){
            good = intent.getSerializableExtra("good") as ProductVo
            Glide.with(this).load(good.imgAddr).into(binding.goodDetailCoverImage)
        } else {
            good = ProductVo(-1,-1,"","","","",0,0,0, LocalDateTime.now(), LocalDateTime.now(),
            0,0,0,0,"","","","","","")
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
                apiService.updateGood(
                    ProductVo(good.productId,null,binding.goodDetailNormalCategoryInput.text.toString(),binding.goodDetailNormalTitleInput.text.toString(),
                    binding.goodDetailNormalDetailInput.text.toString(),
                    if(pictureUpdatedArr[0]){PictureUtils.bitmap2String(binding.goodDetailCoverImage.drawToBitmap(),100)} else {null},
                    Money(binding.goodDetailNormalPriceInput.text.toString()).cent.toInt(),
                    null,null, null, LocalDateTime.now(),null,
                        null,null,binding.goodDetailNormalStockInput.text.toString().toInt(),
                    if (pictureUpdatedArr[1]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage1.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[2]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage2.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[3]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage3.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[4]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage4.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[5]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage5.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[6]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage6.drawToBitmap(),100)} else {null})
                ).enqueue(object : Callback<ApiResponse<ProductVo>>{
                    override fun onResponse(
                        call: Call<ApiResponse<ProductVo>>,
                        response: Response<ApiResponse<ProductVo>>
                    ) {

                        val apiResponse = response.body() as ApiResponse<ProductVo>
                        if (apiResponse.success){
                            apiResponse.data?.let {
                                Log.d(javaClass.simpleName,"添加商品成功 ${it.toString()}")
                                "更新商品成功".shortToast()
                            }
                        } else {
                            Log.d(javaClass.simpleName,apiResponse.message)
                        }

                    }

                    override fun onFailure(call: Call<ApiResponse<ProductVo>>, t: Throwable) {
                        "更新商品失败".shortToast()
                        Log.e(javaClass.simpleName,t.stackTraceToString())
                    }

                })

            } else {
                apiService.newGood(ProductVo(null,null,binding.goodDetailNormalCategoryInput.text.toString(),binding.goodDetailNormalTitleInput.text.toString(),
                    binding.goodDetailNormalDetailInput.text.toString(),
                    if(pictureUpdatedArr[0]){PictureUtils.bitmap2String(binding.goodDetailCoverImage.drawToBitmap(),100)} else {null},
                    Money(binding.goodDetailNormalPriceInput.text.toString()).cent.toInt(),
                    0,0, LocalDateTime.now(), LocalDateTime.now(),0,
                    FlowerSupplierApplication.store.shopId,0,binding.goodDetailNormalStockInput.text.toString().toInt(),
                    if (pictureUpdatedArr[1]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage1.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[2]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage2.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[3]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage3.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[4]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage4.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[5]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage5.drawToBitmap(),100)} else {null},
                    if (pictureUpdatedArr[6]){PictureUtils.bitmap2String(binding.goodDetailShowpictureItemImage6.drawToBitmap(),100)} else {null}
                    )).enqueue(object : Callback<ApiResponse<ProductVo>>{
                    override fun onResponse(
                        call: Call<ApiResponse<ProductVo>>,
                        response: Response<ApiResponse<ProductVo>>
                    ) {

                        val apiResponse = response.body() as ApiResponse<ProductVo>
                        if (apiResponse.success) {
                            apiResponse.data?.let {
                                Log.d(javaClass.simpleName,"添加商品成功 ${it.toString()}")
                                "添加商品成功".shortToast()
                            }

                        } else {
                            Log.d(javaClass.simpleName,apiResponse.message)
                        }

                    }

                    override fun onFailure(call: Call<ApiResponse<ProductVo>>, t: Throwable) {
                        "添加商品失败".shortToast()
                        Log.e(javaClass.simpleName,t.stackTraceToString())
                    }

                })
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