package com.sindrenm.lwb.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sindrenm.lwb.app.home.HomeGraphRoute
import com.sindrenm.lwb.app.home.homeNavGraph
import com.sindrenm.lwb.app.ui.theme.LwbTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            LwbTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = HomeGraphRoute) {
                    homeNavGraph()
                }
            }
        }
    }
}
