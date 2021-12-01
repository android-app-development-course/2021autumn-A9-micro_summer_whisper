package org.coderpwh.micro_summer_whisper.fragments.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_main.*
import org.coderpwh.micro_summer_whisper.R
import org.coderpwh.micro_summer_whisper.models.CommodityTypeModel
import org.w3c.dom.Text
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [fragment_main.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    var bannerList:ArrayList<String> = ArrayList()
    lateinit var br:Banner
    lateinit var gridView1:GridView
    lateinit var viewPagerList:ArrayList<View>
    var listData:ArrayList<CommodityTypeModel> = ArrayList()
    val mPageSize:Int = 8
    var totalPage:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var inflate = inflater.inflate(R.layout.fragment_main, container, false)
        initView(inflate)

        
        initBanner()
        initGv1()


        return inflate
    }


    fun initGv1(){
        this.gridView1.adapter =  object :BaseAdapter(){
            override fun getCount() = 8

            override fun getItem(position: Int): Any {
                return 1
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                var fl = LayoutInflater.from(context).inflate(R.layout.item_gridview, null)
                var tv = fl.findViewById<TextView>(R.id.proName)
                var iv = fl.findViewById<ImageView>(R.id.imgUrl)
                tv.setText("test")
                iv.setImageResource(R.drawable.home53)
                return fl
            }
        }

    }

    fun initView(view:View){
        br = view.findViewById(R.id.banner)
        gridView1 = view.findViewById(R.id.gridView1)
    }
    //初始化广告页面
    fun initBanner(){
        bannerList.clear()
        bannerList.add("http://n.sinaimg.cn/translate/20170405/mcYi-fycxmks5744613.jpg")
        bannerList.add("http://n.sinaimg.cn/translate/20170405/5diM-fycwymx3911184.jpg")
        Log.d("test", bannerList.get(0))
        br.setImages(bannerList)
        br.setImageLoader(object :ImageLoader(){
            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                Glide.with(context).load(path).into(imageView)
            }
        })
        br.start()

    }


}