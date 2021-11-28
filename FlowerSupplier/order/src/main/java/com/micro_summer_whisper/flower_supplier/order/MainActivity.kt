package com.micro_summer_whisper.flower_supplier.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.micro_summer_whisper.flower_supplier.order.databinding.ActivityMainBinding
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}