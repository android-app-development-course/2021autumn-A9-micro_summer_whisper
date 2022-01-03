package com.example.floor_shop.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import com.example.floor_shop.activity.ui.theme.JetsnackTheme
import com.example.floor_shop.ui.MainActivity
import com.example.floor_shop.utils.LoginUtil
import floor_shop.R
import java.util.*


class FirstActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var content = this

        setContent {
            JetsnackTheme {

                // A surface container using the 'background' color from the theme
                ConstraintLayout() {
                    val (button,text) = createRefs()
                    Greeting3("Android",content)

                }

            }
        }
    }
}

@Composable
fun Greeting3(name: String,context: Context) {
    var isLogin = LoginUtil.isLogin(context)
    var intent:Intent? = null
    if (isLogin) {
        intent = Intent(context, MainActivity::class.java)
    }else {
        intent = Intent(context,LoginActivity::class.java)
    }



    var timer = Timer()
    var timerTask = object : TimerTask() {
        override fun run() {
            context.startActivity(intent)
        }
    }
    timer.schedule(timerTask,3000)


    JetsnackTheme() {
        ConstraintLayout {
            val button = createRef()

            Box{
                Image(
                    painter = rememberImagePainter(
                        data = R.drawable.loginshow,
                        builder = {
                            //加了这个解码,如果图片是jpg等格式,是没法成功解析出来的
                            decoder(GifDecoder())
                            crossfade(true)
                        }
                    ),
                    contentDescription = null,
//            contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .background(color = Color(246, 202, 109, 255)),
//            contentScale = ContentScale.FillHeight
                )


            }

            Button(
                onClick = {
                    timer.cancel()
                    context.startActivity(intent)
                    if (context is Activity) {
                        context.finish()
                    }
                },
                modifier = Modifier.constrainAs(button) {
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    end.linkTo(parent.end,20.dp)
                }
                , colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(163, 207, 100, 255)

                )
            ) {
                Text(text = "跳过")

            }

        }




    }
}
