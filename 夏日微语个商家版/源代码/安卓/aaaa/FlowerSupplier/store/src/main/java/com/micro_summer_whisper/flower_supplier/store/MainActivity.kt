package com.micro_summer_whisper.flower_supplier.store

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.micro_summer_whisper.flower_supplier.store.databinding.ActivityMainBinding
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
class MainActivity : BaseActivity() {

    companion object {

        fun actionStart(context:Context) {
            val intent = Intent(context,MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}