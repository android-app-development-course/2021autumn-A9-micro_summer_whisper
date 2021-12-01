package org.coderpwh.micro_summer_whisper.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_classify.*
import org.coderpwh.micro_summer_whisper.R
import org.coderpwh.micro_summer_whisper.adapter.ClassifyAdapter
import org.coderpwh.micro_summer_whisper.adapter.ItemAdapter
import org.coderpwh.micro_summer_whisper.models.Classify
import org.coderpwh.micro_summer_whisper.models.CommodityModel


class ClassifyFragment : Fragment() {
    private val classifyList = ArrayList<Classify>()
    private val itemList = ArrayList<CommodityModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun initClassify(view: View){
        for(id in 1..100){
            classifyList.add(Classify("test$id",id))
        }
        var leftView = view.findViewById<ListView>(R.id.left)
        var classifyAdapter =
            this.activity?.let { ClassifyAdapter(it, R.layout.classify_item, classifyList) }
        leftView.adapter = classifyAdapter
    }

    fun initItem(view: View){
        for (id in 1..20){
            itemList.add(CommodityModel(id.toLong(),"test$id","test$id"
                ,"100",100,100,false
                ,"好吃不上火","${R.drawable.home53}","f"))
        }
        var rightView = view.findViewById<ListView>(R.id.right)
        val rightAdapter = this.activity?.let {
            ItemAdapter(it,R.layout.item_detail,itemList)
        }
        rightView.adapter = rightAdapter
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var inflate = inflater.inflate(R.layout.fragment_classify, container, false)
        initClassify(inflate)
        initItem(inflate)
        return inflate
    }



}