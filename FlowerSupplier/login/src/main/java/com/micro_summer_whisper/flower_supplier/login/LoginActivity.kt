package com.micro_summer_whisper.flower_supplier.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.login.databinding.ActivityLoginBinding
import java.util.regex.Pattern

@Route(path = "/login/login_activity")
class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Autowired
    lateinit var name: String

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*ARouter.getInstance().inject(this)
        Log.d(javaClass.simpleName,"name:$name")*/

        var flag1 = false
        var flag2 = false
        var flag3 = false
        var _phone = ""
        var _password = ""
        binding.loginPhone.addTextChangedListener {
            flag1 = false
            _phone = binding.loginPhone.text.toString()//获取输入
            if (_phone.isEmpty()) {
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
            binding.loginPhone.setText("")
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

        //忘记密码
        binding.loginForget.setOnClickListener {
            "忘记密码".shortToast()
        }

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

        //登录按钮
        binding.loginLogin.setOnClickListener {
            if (binding.loginPhone.text.toString() == "13579246810" && binding.loginPassword.text.toString() == "abc") {
                "登录成功".shortToast()
                finish()
            } else {
                "手机号或密码错误，请重新输入".shortToast()
            }
        }

        //转到密码登录
        binding.loginVerificationCodeLogin.setOnClickListener {
            "验证码登录".shortToast()
            val intent = Intent(this, VerificationCodeLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //转到注册按钮
        binding.loginResign.setOnClickListener {
            "去注册".shortToast()
            val intent = Intent(this, ResignActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}