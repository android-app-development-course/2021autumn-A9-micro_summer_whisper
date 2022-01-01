package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.text.style.TtsSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import cn.hutool.core.math.Money
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.MyDatabaseHelper
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.ProductVo
import com.micro_summer_whisper.flower_supplier.common.shortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GoodAdapter(val context: Context, val goodList: ArrayList<ProductVo>) :
    RecyclerView.Adapter<GoodAdapter.GoodViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    inner class GoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.good_cover_image)
        val titleTV: TextView = itemView.findViewById(R.id.good_title)
        val priceTV: TextView = itemView.findViewById(R.id.good_price)
        val stockTV: TextView = itemView.findViewById(R.id.good_stock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.good_item, parent, false)
        return GoodViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: GoodViewHolder, position: Int) {
        val good = goodList[holder.adapterPosition]
        Glide.with(context).load(good.imgAddr).into(holder.imageView)
        holder.titleTV.setText(good.productName)
        val money = Money((good.normalPrice / 100).toLong(), good.normalPrice % 100)
        holder.priceTV.setText(money.toString())
        holder.stockTV.setText(good.stock.toString())
        holder.itemView.setOnClickListener {
            context.let {
                GoodDetailActivity.actionStart(it, true, good)
            }
        }
        val thisthis = this
        holder.itemView.setOnLongClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setItems(arrayOf("删除"), DialogInterface.OnClickListener { dialogInterface, i ->
                when (i) {
                    0 -> {
                        apiService.removeGood(good.productId)
                            .enqueue(object : Callback<ApiResponse<Any>> {
                                override fun onResponse(
                                    call: Call<ApiResponse<Any>>,
                                    response: Response<ApiResponse<Any>>
                                ) {
                                    val apiResponse = response.body() as ApiResponse<Any>
                                    if (apiResponse.success) {
                                        apiResponse?.let {
                                            Log.d(javaClass.simpleName, "删除商品成功 ${it.toString()}")
                                            "删除商品成功".shortToast()
                                            goodList.remove(goodList[holder.adapterPosition])
                                            thisthis.notifyItemRemoved(holder.adapterPosition)
                                        }
                                    } else {
                                        Log.d(javaClass.simpleName, "删除商品失败")
                                    }

                                }

                                override fun onFailure(call: Call<ApiResponse<Any>>, t: Throwable) {
                                    "删除商品失败".shortToast()
                                }
                            })

                    }
                }
            })
            dialog.show()

            true
        }
    }

    override fun getItemCount(): Int {
        return goodList.size
    }


}

