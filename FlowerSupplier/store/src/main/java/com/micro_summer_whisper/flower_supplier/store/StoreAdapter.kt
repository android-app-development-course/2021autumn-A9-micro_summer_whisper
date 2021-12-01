package com.micro_summer_whisper.flower_supplier.store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class StoreAdapter(val fragment: StoreFragment, val storeDataList:ArrayList<StoreData>): RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    inner class StoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val text1Title: TextView = itemView.findViewById(R.id.store_data_text1_title)
        val text2Title: TextView = itemView.findViewById(R.id.store_data_text2_title)
        val text3Title: TextView = itemView.findViewById(R.id.store_data_text3_title)
        val text4Title: TextView = itemView.findViewById(R.id.store_data_text4_title)
        val text5Title: TextView = itemView.findViewById(R.id.store_data_text5_title)
        val text6Title: TextView = itemView.findViewById(R.id.store_data_text6_title)

        val text1: TextView = itemView.findViewById(R.id.store_data_text1)
        val text2: TextView = itemView.findViewById(R.id.store_data_text2)
        val text3: TextView = itemView.findViewById(R.id.store_data_text3)
        val text4: TextView = itemView.findViewById(R.id.store_data_text4)
        val text5: TextView = itemView.findViewById(R.id.store_data_text5)
        val text6: TextView = itemView.findViewById(R.id.store_data_text6)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.store_data_item, parent,false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val data = storeDataList[position]
        holder?.run {
            text1Title.setText(data.sixTextBoxTitleList[0])
            text2Title.setText(data.sixTextBoxTitleList[1])
            text3Title.setText(data.sixTextBoxTitleList[2])
            text4Title.setText(data.sixTextBoxTitleList[3])
            text5Title.setText(data.sixTextBoxTitleList[4])
            text6Title.setText(data.sixTextBoxTitleList[5])

            text1.setText(data.sixTextBoxContentList[0])
            text2.setText(data.sixTextBoxContentList[1])
            text3.setText(data.sixTextBoxContentList[2])
            text4.setText(data.sixTextBoxContentList[3])
            text5.setText(data.sixTextBoxContentList[4])
            text6.setText(data.sixTextBoxContentList[5])
        }

    }

    override fun getItemCount(): Int {
        return storeDataList.size
    }


}

