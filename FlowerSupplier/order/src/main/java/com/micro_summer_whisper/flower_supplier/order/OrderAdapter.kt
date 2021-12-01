package com.micro_summer_whisper.flower_supplier.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.micro_summer_whisper.flower_supplier.order.R

class OrderAdapter(val orderList: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val goodName: TextView = view.findViewById(R.id.tv_title)
        val statu: TextView = view.findViewById(R.id.tv_wen)
        val price: TextView = view.findViewById(R.id.tv_price)
        val num: TextView = view.findViewById(R.id.tv_num)
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            var order = orderList[position]
            Toast.makeText(
                parent.context,
                "you clicked image ${order.goodName}",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orderList[position]
        holder.goodName.text = order.goodName
        holder.statu.text = when (order.statu) {
            0 -> "交易成功"
            1 -> "待付款"
            2 -> "待发货"
            3 -> "待收货"
            4 -> "交易成功"
            else -> ""
        }
        holder.price.text = "￥" + order.price
        holder.num.text = "共" + order.num + "件"
        holder.image.setImageResource(order.imageId)
    }

    override fun getItemCount() = orderList.size

}