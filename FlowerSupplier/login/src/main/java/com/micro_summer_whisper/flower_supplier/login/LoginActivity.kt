package com.micro_summer_whisper.flower_supplier.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.micro_summer_whisper.flower_supplier.common.BaseActivity
import com.micro_summer_whisper.flower_supplier.login.databinding.ActivityLoginBinding

@Route(path = "/login/login_activity")
class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Autowired
    lateinit var name: String

    companion object {
        fun actionStart(context: Context){
            val intent = Intent(context,LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ARouter.getInstance().inject(this)
        Log.d(javaClass.simpleName,"name:$name")
    }
}