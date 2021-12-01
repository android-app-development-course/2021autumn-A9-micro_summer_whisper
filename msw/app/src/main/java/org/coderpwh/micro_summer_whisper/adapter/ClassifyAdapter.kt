package org.coderpwh.micro_summer_whisper.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.TextView
import org.coderpwh.micro_summer_whisper.R
import org.coderpwh.micro_summer_whisper.models.Classify

class ClassifyAdapter(activity: Activity, val resourceId: Int, data: List<Classify>) :
    ArrayAdapter<Classify>(activity, resourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val classifyName: TextView = view.findViewById(R.id.menu_item_textview)
        val rb: RadioButton = view.findViewById(R.id.radioButton)
        val classify = getItem(position) // 获取当前项的classify实例
        if(classify!=null){
            classifyName.text = classify.name
        }
        return view
    }
}