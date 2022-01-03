package com.micro_summer_whisper.flower_supplier.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.login.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        LoginActivity.actionStart(this)
        finish()
    }
}