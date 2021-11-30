package org.coderpwh.micro_summer_whisper.fragments.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_main.*
import org.coderpwh.micro_summer_whisper.R
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [fragment_main.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    var bannerList:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var inflate = inflater.inflate(R.layout.fragment_main, container, false)
        initBanner(inflate)


        return inflate
    }

    //初始化广告页面
    fun initBanner(view:View){
        var br = view.findViewById<Banner>(R.id.banner)
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