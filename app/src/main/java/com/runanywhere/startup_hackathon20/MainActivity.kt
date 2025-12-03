package com.runanywhere.startup_hackathon20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.runanywhere.startup_hackathon20.navigation.AppNavHost
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Startup_hackathon20Theme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {

    val navController = rememberNavController()

    Surface(modifier = Modifier) {
        AppNavHost(navController = navController)
    }
}
