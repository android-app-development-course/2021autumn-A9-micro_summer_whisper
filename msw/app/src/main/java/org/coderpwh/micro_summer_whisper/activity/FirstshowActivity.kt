package org.coderpwh.micro_summer_whisper.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_firstshow.*
import kotlinx.android.synthetic.main.fragment_fragment4s3.*
import kotlinx.android.synthetic.main.fragment_fragment4s3.timerButton
import org.coderpwh.micro_summer_whisper.MainActivity
import org.coderpwh.micro_summer_whisper.R

class FirstshowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstshow)
        timerButton2.startTimer()
        timerButton2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}