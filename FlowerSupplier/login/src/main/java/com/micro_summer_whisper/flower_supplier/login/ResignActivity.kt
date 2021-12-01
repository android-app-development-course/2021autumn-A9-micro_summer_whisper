package com.micro_summer_whisper.flower_supplier.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.login.databinding.ActivityResignBinding
import java.util.regex.Pattern

class ResignActivity : BaseActivity() {
    private lateinit var binding: ActivityResignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var _username = ""
        var _password = ""
        var _verification = ""
        var flag = false           //密码正确与否的标志
        var flag2 = false          //用户名正确与否的标志
        var flag3 = false          //是否勾选阅读协议的标志
        var flag4 = false          //是否输入验证码的标志

        binding.resignPhone.addTextChangedListener {
            flag2 = false
            _username = binding.resignPhone.text.toString()//获取输入
            val compile_phone =
                Pattern.compile("^1[3|4|5|7|8][0-9]{9}\$")   //手机号
            if (compile_phone.matcher(_username).matches()) {
                flag2 = true
                binding.resignDelete1.isEnabled = true
                binding.resignDelete1.visibility = View.VISIBLE
            } else if (_username.isNotEmpty()) {
                binding.resignDelete1.isEnabled = true
                binding.resignDelete1.visibility = View.VISIBLE
                binding.resignPhone.setError("请输入正确的11位手机号码")
            } else {
                binding.resignDelete1.isEnabled = false
                binding.resignDelete1.visibility = View.INVISIBLE
                binding.resignPhone.setError("请输入正确的11位手机号码")
            }
            binding.resignResign.isEnabled = flag && flag2 && flag3 && flag4
            if (binding.resignResign.isEnabled) {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        //清空手机号
        binding.resignDelete1.setOnClickListener {
            binding.resignPhone.setText("")
        }

        //验证码
        binding.resignVerification.addTextChangedListener {
            flag4 = false
            _verification = binding.resignVerification.text.toString()//获取输入
            if (_verification.length > 0) {
                flag4 = true
            }
            binding.resignResign.isEnabled = flag && flag2 && flag3 && flag4
            if (binding.resignResign.isEnabled) {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        binding.resignGetVerification.setOnClickListener {
            "获取验证码".shortToast()
        }

        //密码框：得到焦点时的显示内容
        binding.resignPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (binding.resignPassword.text.isEmpty())
                    binding.resignPassword.setError("密码长度至少为6，至少1个大写字母，1个小写字母和1个数字", null)
            }
        }

        //密码框：输入变化时判断密码是否符合规定
        binding.resignPassword.addTextChangedListener{
            flag = false
            _password = binding.resignPassword.text.toString()//获取输入
            val compile = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}\$")
            val miss_num = Pattern.compile("^[a-zA-Z]{6,}\$")//缺数字
            val miss_upper = Pattern.compile("^[a-z\\d]{6,}\$")//缺大写
            val miss_lower = Pattern.compile("^[A-Z\\d]{6,}\$")//缺小写
            val miss_upper_num = Pattern.compile("^[a-z]{6,}\$")//缺大写、数字
            val miss_lower_upper = Pattern.compile("^[\\d]{6,}\$")//缺小写、大写
            val miss_lower_num = Pattern.compile("^[A-Z]{6,}\$")//缺小写、数字

            var _delete = true

            if (_password.length < 6) {
                if (_password.length == 0) {
                    _delete = false
                }
                binding.resignPassword.setError("密码长度小于6")
            } else if (miss_upper_num.matcher(_password).matches()) {
                binding.resignPassword.setError("缺少大写字母、数字")
            } else if (miss_lower_upper.matcher(_password).matches()) {
                binding.resignPassword.setError("缺少小写字母、大写字母")
            } else if (miss_lower_num.matcher(_password).matches()) {
                binding.resignPassword.setError("缺少小写字母、数字")
            } else if (miss_upper.matcher(_password).matches()) {
                binding.resignPassword.setError("缺少大写字母")
            } else if (miss_lower.matcher(_password).matches()) {
                binding.resignPassword.setError("缺少小写字母")
            } else if (miss_num.matcher(_password).matches()) {
                binding.resignPassword.setError("缺少数字")
            } else if (compile.matcher(_password).matches()) {
                flag = true
            } else {
                binding.resignPassword.setError("不能包含特殊符号")
            }

            if (_delete) {
                binding.resignDelete2.isEnabled = true
                binding.resignDelete2.visibility = View.VISIBLE
            } else {
                binding.resignDelete2.isEnabled = false
                binding.resignDelete2.visibility = View.INVISIBLE
            }
            binding.resignResign.isEnabled = flag && flag2 && flag3 && flag4
            if (binding.resignResign.isEnabled) {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        //清空密码
        binding.resignDelete2.setOnClickListener {
            binding.resignPassword.setText("")
        }

        //复选框
        binding.resignRead2.setOnCheckedChangeListener { compoundButton, b ->
            flag3 = b
            binding.resignResign.isEnabled = flag && flag2 && flag3 && flag4
            if (binding.resignResign.isEnabled) {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        //注册按钮
        binding.resignResign.setOnClickListener {
            "注册成功".shortToast()
            finish()
        }
    }
}