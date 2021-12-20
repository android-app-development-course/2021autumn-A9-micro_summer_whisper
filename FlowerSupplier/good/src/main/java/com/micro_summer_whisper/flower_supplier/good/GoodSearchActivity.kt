package com.micro_summer_whisper.flower_supplier.good

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.good.databinding.ActivityGoodSearchBinding

class GoodSearchActivity : BaseActivity() {

    private lateinit var binding: ActivityGoodSearchBinding

    companion object {
        fun actionStart(context: Context){
            val intent = Intent(context,GoodSearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoodSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.goodSearchToolbar)
        val searchView = layoutInflater.inflate(R.layout.good_search_input, null, false)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayShowCustomEnabled(true)
            it.setCustomView(searchView)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.good_search_toolbar,menu)
        return true
    }

}