package org.coderpwh.micro_summer_whisper.design

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.AttributeSet
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.button.MaterialButton
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import org.coderpwh.micro_summer_whisper.MainActivity
import org.coderpwh.micro_summer_whisper.R

class TimerButton(context: Context, attrs: AttributeSet) : QMUIRoundButton(context, attrs) {
    private val duration: Long     //计时总长,默认120s
    private val interval: Long    //计时间隔,默认1s
    val activity=context as Activity
    //主构造函数逻辑
    init {
        isAllCaps = false   //字母小写
        //解析自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimerButton)
        duration = (1000 * typedArray.getInteger(R.styleable.TimerButton_duration, 3)).toLong()
        interval = (1000 * typedArray.getInteger(R.styleable.TimerButton_interval, 1)).toLong()
        typedArray.recycle();
    }

    //按钮封装了一个 CountDownTimer，它来帮我们计时
    private val countDownTimer: CountDownTimer by lazy {
        object : CountDownTimer(duration, interval) {
            override fun onFinish() {
                isEnabled = false
                text = "0s"
                val intent = Intent(activity,MainActivity::class.java)
                context.startActivity(intent)
                activity?.finish()

            }

            override fun onTick(t: Long) {
                text = (t/1000).toString() + "s"
            }
        }
    }

    /**
     * 开始倒计时,同时按钮不可按
     */
    fun startTimer(){
        isEnabled = false
        countDownTimer.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        countDownTimer.cancel()   //防止内存泄漏
    }

}
