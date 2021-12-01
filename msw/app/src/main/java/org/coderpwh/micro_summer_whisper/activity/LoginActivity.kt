package org.coderpwh.micro_summer_whisper.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.coderpwh.micro_summer_whisper.MainActivity
import org.coderpwh.micro_summer_whisper.R

class LoginActivity : AppCompatActivity() {
    private lateinit var account: String
    private lateinit var password: String
    private val userId:Int = 0
    private val handler:Handler = object :Handler(){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                1->onLoginSuccess(msg.obj.toString())
                2->onLoginFailure(msg.obj.toString())
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        btn_login.setOnClickListener {
            login()
        }
    }

    fun login(){
        account = input_mobile.text.toString()
        password = input_password.text.toString()
        postLoginRequest(account,password)
    }

    fun postLoginRequest(account:String,password:String){
        //使用okhttp进行请求
        //TODO

        var validate = validate()
        var message = Message.obtain()
        if(validate){
            message.what = 1
            message.obj = "成功登录"
        }else{
            message.what = 2
            message.obj = "登录失败"
        }
        handler.handleMessage(message)


    }

    fun validate():Boolean{
        var flag:Boolean = true
        if(account.isEmpty()){
            input_mobile.setError("账号不能为空")
            flag = false
        }
        if (password.isEmpty()||password.length<4||password.length>15){
            input_password.setError("请输入4-10位的密码")
            flag = false
        }
        return flag
    }
//成功登录调用
    fun onLoginSuccess(msg:String){
    Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
    btn_login.setEnabled(true)
    val sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE) //私有数据

    //获取SharedPreferences编辑器
    //获取SharedPreferences编辑器
    val editor = sharedPreferences.edit()
    editor.putString("account", account)
    editor.putString("username", account)
    editor.putString("password", password)
    editor.putInt("userId", userId)
    editor.commit() //提交修改


    val intent = Intent(this@LoginActivity, MainActivity::class.java)
    intent.putExtra("account", account)
    startActivity(intent)
    finish()

    }
    //登录失败调用
    fun onLoginFailure(msg:String){
        Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
        btn_login.isEnabled = true
    }
}