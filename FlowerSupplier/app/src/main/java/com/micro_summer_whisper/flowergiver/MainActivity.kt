package com.micro_summer_whisper.flowergiver

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.micro_summer_whisper.flower_supplier.chat.ChatFragment
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.good.GoodFragment

import com.micro_summer_whisper.flower_supplier.me.MeFragment
import com.micro_summer_whisper.flower_supplier.order.OrderFragment
import com.micro_summer_whisper.flower_supplier.store.StoreFragment
import com.micro_summer_whisper.flowergiver.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding

    private val fragments = arrayOfNulls<Fragment>(5)
    private var currentIndex = 0

    companion object {
        fun actionStart(context: Context){
            val intent = Intent(context,MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initFragment()
        binding.navigation.setOnItemSelectedListener(this)
    }

    private fun initFragment(){
        val fragmentTran = supportFragmentManager.beginTransaction()
        if (fragments[0]==null){
            fragments[0] = StoreFragment.newInstance()
            fragments[0]?.let { fragmentTran.add(R.id.content, it,"storeModule").commit() }
        } else {
            fragments[0]?.let { fragmentTran.show(it) }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_store -> {
                if (currentIndex==0){
                    return true
                }
                val fragmentTran = supportFragmentManager.beginTransaction()
                hideAndShow(0,fragmentTran)
                return true
            }
            R.id.navigation_good -> {
                if (currentIndex==1){
                    return true
                }
                val fragmentTran = supportFragmentManager.beginTransaction()
                if (fragments[1]==null){
                    fragments[1] = GoodFragment.newInstance()
                    fragments[1]?.let { fragmentTran.add(R.id.content, it,"goodModule") }
                }
                hideAndShow(1,fragmentTran)
                return true
            }
            R.id.navigation_order -> {
                if (currentIndex==2){
                    return true
                }
                val fragmentTran = supportFragmentManager.beginTransaction()
                if (fragments[2]==null){
                    fragments[2] = OrderFragment.newInstance()
                    fragments[2]?.let { fragmentTran.add(R.id.content, it,"orderModule") }
                }
                hideAndShow(2,fragmentTran)
                return true
            }
            R.id.navigation_chat -> {
                if (currentIndex==3){
                    return true
                }
                val fragmentTran = supportFragmentManager.beginTransaction()
                if (fragments[3]==null){
                    fragments[3] = ChatFragment.newInstance()
                    fragments[3]?.let { fragmentTran.add(R.id.content, it,"chatModule") }
                }
                hideAndShow(3,fragmentTran)
                return true
            }
            R.id.navigation_me -> {
                if (currentIndex==4){
                    return true
                }
                val fragmentTran = supportFragmentManager.beginTransaction()
                if (fragments[4]==null){
                    fragments[4] = MeFragment.newInstance()
                    fragments[4]?.let { fragmentTran.add(R.id.content, it,"meModule") }
                }
                hideAndShow(4,fragmentTran)
                if (!FlowerSupplierApplication.isLogin){
                    val notifyLoginDialog = AlertDialog.Builder(this)
                    notifyLoginDialog.setPositiveButton("去登录") { dialog, which ->
                        "登录".shortToast()
                        ARouter.getInstance().build("/login/login_activity").navigation();
                    }
                    notifyLoginDialog.show()
                }

                return true
            }
            else -> {
                return false
            }
        }
    }

    private fun hideAndShow(expectIndex: Int, transaction: FragmentTransaction){
        for (i in 0 until fragments.size) {
            if (i != expectIndex && fragments[i] != null) {
                fragments[i]?.let { transaction.hide(it) }
            }
        }
        fragments[expectIndex]?.let { transaction.show(it) }
        transaction.commit()
        currentIndex = expectIndex
    }
}