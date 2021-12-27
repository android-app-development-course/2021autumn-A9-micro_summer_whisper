package com.micro_summer_whisper.flower_supplier.login

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Account
import com.micro_summer_whisper.flower_supplier.common.pojo.OrderVo
import com.micro_summer_whisper.flower_supplier.common.pojo.Person
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.login.databinding.ActivityResignBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class ResignActivity : BaseActivity() {
    private lateinit var binding: ActivityResignBinding

    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
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

        binding.resignEmail.addTextChangedListener {
            flag2 = false
            _username = binding.resignEmail.text.toString()//获取输入
            val compile_email =
                Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})\$")   //手机号
            if (compile_email.matcher(_username).matches()) {
                flag2 = true
                binding.resignDelete1.isEnabled = true
                binding.resignDelete1.visibility = View.VISIBLE
            } else if (_username.isNotEmpty()) {
                binding.resignDelete1.isEnabled = true
                binding.resignDelete1.visibility = View.VISIBLE
                binding.resignEmail.setError("请输入正确的邮箱")
            } else {
                binding.resignDelete1.isEnabled = false
                binding.resignDelete1.visibility = View.INVISIBLE
                binding.resignEmail.setError("请输入正确的邮箱")
            }
            binding.resignResign.isEnabled = flag && flag2 && flag3 && flag4
            if (binding.resignResign.isEnabled) {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.resignResign.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        //清空邮箱
        binding.resignDelete1.setOnClickListener {
            binding.resignEmail.setText("")
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

        //916819054@qq.com
        binding.resignGetVerification.setOnClickListener {
            val ac = mapOf("userName" to binding.resignEmail.text.toString())
            apiService.getCode(ac).enqueue(object :
                Callback<ApiResponse<Person>> {
                override fun onResponse(
                    call: Call<ApiResponse<Person>>,
                    response: Response<ApiResponse<Person>>
                ) {
                    val apiResponse = response.body() as ApiResponse<Person>
                    if (apiResponse.success) {
                        apiResponse.data?.let {
                            Log.d(javaClass.simpleName, "获取验证码成功 ${it.toString()}")
                            "获取验证码成功".shortToast()
                        }

                    } else {
                        Log.d(javaClass.simpleName, apiResponse.message)
                    }
                }

                override fun onFailure(call: Call<ApiResponse<Person>>, t: Throwable) {
                    "获取验证码失败".shortToast()
                    Log.e(javaClass.simpleName, t.stackTraceToString())
                }
            })


        }

        //密码框：得到焦点时的显示内容
        binding.resignPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (binding.resignPassword.text.isEmpty())
                    binding.resignPassword.setError("密码长度至少为6，至少1个大写字母，1个小写字母和1个数字", null)
            }
        }

        //密码框：输入变化时判断密码是否符合规定
        binding.resignPassword.addTextChangedListener {
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
            val ac = mapOf(
                "userName" to binding.resignEmail.text.toString(),
                "cd" to binding.resignVerification.text.toString(),
                "password" to binding.resignPassword.text.toString()
            )
            apiService.register(ac).enqueue(object :
                Callback<ApiResponse<Person>> {
                override fun onResponse(
                    call: Call<ApiResponse<Person>>,
                    response: Response<ApiResponse<Person>>
                ) {
                    val apiResponse = response.body() as ApiResponse<Person>
                    if (apiResponse.success) {
                        apiResponse.data?.let {
                            Log.d(javaClass.simpleName, "获取验证码成功 ${it.toString()}")
//                            "获取验证码成功".shortToast()
                        }

                    } else {
                        Log.d(javaClass.simpleName, apiResponse.message)
                    }
                }

                override fun onFailure(call: Call<ApiResponse<Person>>, t: Throwable) {
                    "获取验证码失败".shortToast()
                    Log.e(javaClass.simpleName, t.stackTraceToString())
                }
            })
            finish()
        }
    }
}