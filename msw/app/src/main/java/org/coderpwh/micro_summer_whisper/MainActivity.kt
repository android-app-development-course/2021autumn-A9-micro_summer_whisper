package org.coderpwh.micro_summer_whisper

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_guide.*
import kotlinx.android.synthetic.main.content_main.*
import org.coderpwh.micro_summer_whisper.fragments.main.ClassifyFragment
import org.coderpwh.micro_summer_whisper.fragments.main.MainFragment
import org.coderpwh.micro_summer_whisper.fragments.main.MeFragment
import org.coderpwh.micro_summer_whisper.fragments.main.SqFragment

class MainActivity : AppCompatActivity() {

    private var exitTime = 0
    companion object {
        val activityList:ArrayList<Activity> = ArrayList()
        fun exitApp(){
            activityList.forEach {
                it.finish()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        activityList.add(this)

        init()

    }
    //绑定控件
    fun init(){
        var mFragments = ArrayList<Fragment>();
        mFragments.add(MainFragment())
        mFragments.add(ClassifyFragment())
        mFragments.add(SqFragment())
        mFragments.add(MeFragment())

        viewPage.setAdapter(object : FragmentPagerAdapter(supportFragmentManager){
            override fun getCount() = mFragments.size


            override fun getItem(position: Int): Fragment {
                return mFragments.get(position)
            }
        })
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.imageButton1->viewPage.setCurrentItem(0)
                R.id.imageButton2->viewPage.setCurrentItem(1)
                R.id.imageButton3->viewPage.setCurrentItem(2)
                R.id.imageButton5->viewPage.setCurrentItem(3)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK
            && event.action == KeyEvent.ACTION_DOWN
        ) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(applicationContext, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis().toInt()
            } else {
                MainActivity.exitApp()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}