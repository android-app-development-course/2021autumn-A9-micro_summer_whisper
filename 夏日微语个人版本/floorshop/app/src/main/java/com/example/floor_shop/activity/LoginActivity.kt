package com.example.floor_shop.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import floor_shop.R
import com.example.floor_shop.activity.ui.theme.JetsnackTheme
import com.example.floor_shop.ui.MainActivity
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import kotlin.concurrent.thread


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContent {
            JetsnackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LoginPageDemo(this)
                }
            }
        }
    }
}




@Composable
fun LoginPageDemo(c: Context) {
    var name by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }

    val pwdVisualTransformation = PasswordVisualTransformation()
    var showPwd by remember {
        mutableStateOf(true)
    }

    val transformation = if (showPwd) pwdVisualTransformation else VisualTransformation.None

    MaterialTheme {

        Box(Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter("https://img.zcool.cn/community/01d46d5d1ca689a801213763ef5aa9.jpg@1280w_1l_2o_100sh.jpg"), contentDescription = null,
                modifier = Modifier.fillMaxSize().fillMaxHeight(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "注册",
                color = Color(165, 209, 8, 205),
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            )
            Column() {
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .background(Color.White)
                        .padding(40.dp)
                        .fillMaxWidth()
                ) {
                    Column() {
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = name,
                            placeholder = {
                                Text("请输入用户名")
                            },
                            onValueChange = { str -> name = str },
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.AccountBox,
                                    contentDescription = null
                                )
                            })
                        TextField(
                            value = pwd, onValueChange = { str -> pwd = str },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text("请输入密码")
                            },
                            visualTransformation = transformation,
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null
                                )
                            }, trailingIcon = {
                                if (showPwd) {
                                    IconButton(onClick = { showPwd = !showPwd }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.eye_fill),
                                            contentDescription = null,
                                            Modifier.size(30.dp)
                                        )
                                    }
                                } else {
                                    IconButton(onClick = { showPwd = !showPwd }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.eye_close),
                                            contentDescription = null,
                                            Modifier.size(30.dp)
                                        )
                                    }
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()) {
                        Text(text = "快捷登录", fontSize = 16.sp, color = Color.Gray)
                        Text(text = "忘记密码", fontSize = 16.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {

                            thread {
                                //登录
                                val gson = Gson()
                                val client = OkHttpClient()
                                val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
                                val mp = HashMap<String,String>()
                                mp.put("userName",name)
                                mp.put("password",pwd)
                                val jsonStr = gson.toJson(mp)
                                val requestBody = RequestBody.create(JSON,jsonStr)
                                val request = Request.Builder()
                                    .url("http://10.242.104.239:8080/account/login") //http://10.242.104.239:8080/account/login
                                    .post(requestBody)
                                    .build()
                                val response = client.newCall(request).execute()
                                val responseData = response.body?.string()
                                var flag = false
                                if (responseData!=null) {
                                    if (responseData.contains("success")) {
                                        flag = true
                                    }
                                }

                                if (flag) {
                                    val sharedPreferences = c.getSharedPreferences("userInfo",
                                        AppCompatActivity.MODE_PRIVATE
                                    ) //私有数据

                                    //获取SharedPreferences编辑器
                                    //获取SharedPreferences编辑器
                                    val editor = sharedPreferences.edit()
                                    editor.putString("account", name)
                                    editor.putString("password", pwd)
                                    editor.commit() //提交修改

                                    var intent = Intent(c,MainActivity::class.java)
                                    c.startActivity(intent)
                                    if (c is Activity) {
                                        c.finish()
                                    }
//                                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(c, "登录失败", Toast.LENGTH_SHORT).show()
                                    Looper.loop()
                                }

                            }


                        },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5c59fe)),
                        contentPadding = PaddingValues(12.dp, 16.dp)
                    ) {
                        Text("登录", color = Color.White, fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
                        Row(
                            Modifier
                                .height(1.dp)
                                .weight(1f)
                                .background(Color(0xFFCFC5C5))
                                .padding(end = 10.dp)){}
                        Text(text = "第三方登录", fontSize = 16.sp, color = Color.Gray)
                        Row(
                            Modifier
                                .height(1.dp)
                                .weight(1f)
                                .background(Color(0xFFCFC5C5))
                                .padding(start = 10.dp)){}
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.qq),
                                contentDescription = null
                            )
                            Text(
                                "QQ",
                                color = Color(0xffcdcdcd),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.wx),
                                contentDescription = null
                            )
                            Text(
                                "微信",
                                color = Color(0xffcdcdcd),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.tb),
                                contentDescription = null
                            )
                            Text(
                                "淘宝",
                                color = Color(0xffcdcdcd),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                }
            }
        }
    }
}