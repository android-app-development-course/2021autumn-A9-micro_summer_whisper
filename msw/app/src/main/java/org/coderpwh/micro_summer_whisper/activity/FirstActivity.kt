package org.coderpwh.micro_summer_whisper.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.widget.EditText
import org.coderpwh.micro_summer_whisper.MainActivity
import org.coderpwh.micro_summer_whisper.R
import org.coderpwh.micro_summer_whisper.utils.LoginCheckUtil
import java.util.prefs.AbstractPreferences

class FirstActivity : AppCompatActivity() {
    private final val  SPLASH_DISPLAY_LENGHT:Int = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        supportActionBar?.hide()
        var sharedPreferences = getSharedPreferences("guide", Context.MODE_PRIVATE)
        Handler().postDelayed(Runnable {
            if(sharedPreferences.getBoolean("FirstActivity",true)){
                var editor = sharedPreferences.edit()
                editor.putBoolean("FirstActivity",false)
                editor.commit()
                // 启动GuideActivity
                var intent = Intent(this, GuideActivity::class.java)
                startActivity(intent)
                finish()


            }else{
                var isLogin = LoginCheckUtil.isLogin(this)
                if(isLogin){
                    val intent = Intent(this,MainActivity::class.java)
                }else{
                    val intent = Intent(this,LoginActivity::class.java)
                }

                startActivity(intent)
                finish()
            }
        }, SPLASH_DISPLAY_LENGHT.toLong())
    }
}