package com.micro_summer_whisper.flower_supplier.chat

import android.os.Bundle
import com.micro_summer_whisper.flower_supplier.chat.databinding.ActivityMainBinding
import com.micro_summer_whisper.flower_supplier.common.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}