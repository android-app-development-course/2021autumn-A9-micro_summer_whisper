

package com.example.floor_shop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.floor_shop.ui.home.diary.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel by viewModels<TodoViewModel>()
        // This app draws behind the system bars, so we want to handle fitting system windows
        //crab a beverage and check back later
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            JetsnackApp(todoViewModel)
        }
    }
}
