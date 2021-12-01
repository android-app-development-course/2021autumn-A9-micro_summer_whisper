package org.coderpwh.micro_summer_whisper.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.coderpwh.micro_summer_whisper.R
import org.coderpwh.micro_summer_whisper.models.CommodityModel


class ItemAdapter(activity: Activity, val resourceId: Int, data: List<CommodityModel>) :
    ArrayAdapter<CommodityModel>(activity, resourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val itemName: TextView = view.findViewById(R.id.commodityName)
        var itemInfo = view.findViewById<TextView>(R.id.commodityInfo)
        var itemPrice = view.findViewById<TextView>(R.id.commodityPrice)
        var itemImg = view.findViewById<ImageView>(R.id.commodityImg)
        val item = getItem(position) // 获取当前项的classify实例
        if(item!=null){
            itemName.text = item.name
            itemInfo.text = item.info
            itemPrice.text = item.price
            itemImg.setImageResource(item.img.toInt())
        }
        return view
    }
}