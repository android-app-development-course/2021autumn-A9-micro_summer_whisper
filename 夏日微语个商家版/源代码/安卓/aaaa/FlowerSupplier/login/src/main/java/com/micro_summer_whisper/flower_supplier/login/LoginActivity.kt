package com.micro_summer_whisper.flower_supplier.login

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.MyDatabaseHelper
import com.micro_summer_whisper.flower_supplier.common.conditon.OrderCondition
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Person
import com.micro_summer_whisper.flower_supplier.common.pojo.PersonVo
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.login.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Route(path = "/login/login_activity")
class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

    val dbHelper = MyDatabaseHelper(this, "flower_supplier", 1)//取得可写的数据库

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*ARouter.getInstance().inject(this)
        Log.d(javaClass.simpleName,"name:$name")*/

        var flag1 = false
        var flag2 = false
        var flag3 = false
        var _email = ""
        var _password = ""
        binding.loginEmail.addTextChangedListener {
            flag1 = false
            _email = binding.loginEmail.text.toString()//获取输入
            if (_email.isEmpty()) {
                binding.loginDelete1.isEnabled = false
                binding.loginDelete1.visibility = View.INVISIBLE
            } else {
                flag1 = true
                binding.loginDelete1.isEnabled = true
                binding.loginDelete1.visibility = View.VISIBLE
            }

            binding.loginLogin.isEnabled = flag1 && flag2 && flag3
            if (binding.loginLogin.isEnabled) {
                binding.loginLogin.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.loginLogin.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        binding.loginDelete1.setOnClickListener {
            binding.loginEmail.setText("")
        }

        binding.loginPassword.addTextChangedListener {
            flag2 = false
            _password = binding.loginPassword.text.toString()//获取输入
            if (_password.length > 0) {
                flag2 = true
                binding.loginDelete.isEnabled = true
                binding.loginDelete.visibility = View.VISIBLE
            } else {
                binding.loginDelete.isEnabled = false
                binding.loginDelete.visibility = View.INVISIBLE
            }
            binding.loginLogin.isEnabled = flag1 && flag2 && flag3
            if (binding.loginLogin.isEnabled) {
                binding.loginLogin.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.loginLogin.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        /* //忘记密码
         binding.loginForget.setOnClickListener {
             "忘记密码".shortToast()
         }*/

        binding.loginDelete.setOnClickListener {
            binding.loginPassword.setText("")
        }

        binding.loginRead.setOnCheckedChangeListener { compoundButton, b ->
            flag3 = b
            binding.loginLogin.isEnabled = flag1 && flag2 && flag3
            if (binding.loginLogin.isEnabled) {
                binding.loginLogin.setBackgroundResource(R.drawable.button_circle_shape)
            } else {
                binding.loginLogin.setBackgroundResource(R.drawable.button_circle_shape_gray)
            }
        }

        //916819054@qq.com
        //登录按钮
        binding.loginLogin.setOnClickListener {
            val personVo = PersonVo()
            personVo.userName = binding.loginEmail.text.toString()
            personVo.password = binding.loginPassword.text.toString()
            apiService.login(personVo).enqueue(object :
                Callback<ApiResponse<PersonVo>> {
                override fun onResponse(
                    call: Call<ApiResponse<PersonVo>>,
                    response: Response<ApiResponse<PersonVo>>
                ) {
                    val apiResponse = response.body() as ApiResponse<PersonVo>
                    if (apiResponse.success) {
                        apiResponse.data?.let {
                            FlowerSupplierApplication.userInfo = it
                            FlowerSupplierApplication.UserId = it.userId
                            FlowerSupplierApplication.isLogin = true
                            FlowerSupplierApplication.TOKEN = it.token
                            /*val db = dbHelper.writableDatabase
                            val value = ContentValues().apply {
                                put("userid", it.userId.toString())
                            }
                            db.insert("USERID", null, value)*/
                            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
                            editor.putInt("userid", it.userId)
                            editor.putString("token", FlowerSupplierApplication.TOKEN)
                            editor.apply()

                            Log.d(javaClass.simpleName, "登录成功 ${it.toString()}")
                            "登录成功".shortToast()
                            finish()
                            ARouter.getInstance().build("/app/MainActivity").navigation();
                        }

                    } else {
                        Log.d(javaClass.simpleName, apiResponse.message)
                    }
                }

                override fun onFailure(call: Call<ApiResponse<PersonVo>>, t: Throwable) {
                    "登录失败".shortToast()
                    Log.e(javaClass.simpleName, t.stackTraceToString())
                }
            })

//            finish()
        }

        /* //转到验证码登录
         binding.loginVerificationCodeLogin.setOnClickListener {
             "验证码登录".shortToast()
             val intent = Intent(this, VerificationCodeLoginActivity::class.java)
             startActivity(intent)
             finish()
         }*/

        //转到注册按钮
        binding.loginResign.setOnClickListener {
            "去注册".shortToast()
            val intent = Intent(this, ResignActivity::class.java)
            startActivity(intent)
        }
    }
}