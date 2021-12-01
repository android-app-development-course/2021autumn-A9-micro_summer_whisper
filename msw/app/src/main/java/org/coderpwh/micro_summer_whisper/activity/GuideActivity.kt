package org.coderpwh.micro_summer_whisper.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_guide.*
import org.coderpwh.micro_summer_whisper.R
import org.coderpwh.micro_summer_whisper.fragments.start.Fragment4s1
import org.coderpwh.micro_summer_whisper.fragments.start.Fragment4s2
import org.coderpwh.micro_summer_whisper.fragments.start.Fragment4s3

class GuideActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        initView()
    }
    fun initView() {
        val fragments:ArrayList<Fragment> = ArrayList()
        fragments.add(Fragment4s1())
        fragments.add(Fragment4s2())
        fragments.add(Fragment4s3())
        viewpage.setAdapter(object : FragmentPagerAdapter(supportFragmentManager){
            override fun getCount() = fragments.size
            override fun getItem(position: Int): Fragment {
                return fragments.get(position)
            }
        })
    }

}