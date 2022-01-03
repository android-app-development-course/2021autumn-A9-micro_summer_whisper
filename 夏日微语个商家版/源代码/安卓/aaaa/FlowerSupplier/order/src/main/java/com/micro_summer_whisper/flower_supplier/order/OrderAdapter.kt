package com.micro_summer_whisper.flower_supplier.order

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cn.hutool.core.math.Money
import com.bumptech.glide.Glide
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.OrderVo
import com.micro_summer_whisper.flower_supplier.order.OrderDetailActivity.Companion.ORDER_ID
import com.micro_summer_whisper.flower_supplier.order.OrderDetailActivity.Companion.SHOP_ID
import com.micro_summer_whisper.flower_supplier.order.databinding.Fragment0Binding

class OrderAdapter(val context: Context, val orderList: List<OrderVo>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val receiver: TextView = view.findViewById(R.id.tv_receiver)
        val goodName: TextView = view.findViewById(R.id.tv_title)
        val statu: TextView = view.findViewById(R.id.tv_wen)
        val price: TextView = view.findViewById(R.id.tv_price)
        val num: TextView = view.findViewById(R.id.tv_num)
        val image: ImageView = view.findViewById(R.id.tv_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.order_item, parent, false)

        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            var order = orderList[position]
            val intent = Intent(context, OrderDetailActivity::class.java)
            intent.putExtra(ORDER_ID, order.orderId)
            intent.putExtra(SHOP_ID, order.shopId)
            context.startActivity(intent)
        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orderList[holder.adapterPosition]
        Glide.with(context).load(order.productImg).into(holder.image)

        holder.goodName.text = order.productName
        holder.statu.text = when (order.orderState) {
            0 -> "交易成功"
            1 -> "待付款"
            2 -> "待发货"
            3 -> "待收货"
            else -> ""
        }
        holder.price.text =
            "￥" + Money(
                order.onePrice.toLong() * order.amount / 100,
                order.onePrice * order.amount % 100
            ).toString()
        holder.num.text = "共" + order.amount + "件"
        holder.receiver.text = order.receiverName

        /* holder.itemView.setOnClickListener {
             context.let {
                 OrderDetailActivity.actionStart(it, true, good)
             }
         }*/

    }

    override fun getItemCount() = orderList.size

}