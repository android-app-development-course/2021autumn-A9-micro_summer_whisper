package com.micro_summer_whisper.flower_supplier.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.login.databinding.ActivityVerificationBinding

import java.util.regex.Pattern

class VerificationCodeLoginActivity : BaseActivity() {
    private lateinit var binding: ActivityVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var flag1 = false
        var flag2 = false
        var flag3 = false
        var _phone = ""
        var _verification = ""
        binding.loginPhone2.addTextChangedListener {
            flag1 = false
            _phone = binding.loginPhone2.text.toString()//获取输入
            if (_phone.isEmpty()) {
                binding.loginDelete2.isEnabled = false
                binding.loginDelete2.visibility = View.INVISIBLE
            } else {
                flag1 = true
                binding.loginDelete2.isEnabled = true
                binding.loginDelete2.visibility = View.VISIBLE
            }

            binding.loginLogin2.isEnabled = flag1 && flag2 && flag3
            if (binding.loginLogin2.isEnabled) {
                binding.loginLogin2.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.loginLogin2.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        binding.loginDelete2.setOnClickListener {
            binding.loginPhone2.setText("")
        }

        binding.loginVerification.addTextChangedListener {
            flag2 = false
            _verification = binding.loginVerification.text.toString()//获取输入
            if (_verification.length > 0) {
                flag2 = true
            }
            binding.loginLogin2.isEnabled = flag1 && flag2 && flag3
            if (binding.loginLogin2.isEnabled) {
                binding.loginLogin2.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.loginLogin2.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        binding.loginGetVerification.setOnClickListener {
            "获取验证码".shortToast()
        }

        binding.loginRead2.setOnCheckedChangeListener { compoundButton, b ->
            flag3 = b
            binding.loginLogin2.isEnabled = flag1 && flag2 && flag3
            if (binding.loginLogin2.isEnabled) {
                binding.loginLogin2.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.loginLogin2.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        binding.loginGetVerification.setOnClickListener {
            "获取验证码".shortToast()
        }

        //转到密码登录
        binding.loginPasswordCodeLogin.setOnClickListener {
            "密码登录".shortToast()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //转到注册按钮
        binding.loginResign2.setOnClickListener {
            "去注册".shortToast()
            val intent = Intent(this, ResignActivity::class.java)
            startActivity(intent)
            finish()
        }

        //登录按钮
        binding.loginLogin2.setOnClickListener {
            if (binding.loginPhone2.text.toString() == "13579246810" && binding.loginVerification.text.toString() == "1234") {
                "登录成功".shortToast()
                finish()
            } else {
                "验证码错误".shortToast()
            }
        }
    }
}