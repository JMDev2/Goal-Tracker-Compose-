package com.example.targetsavings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.targetsavings.screens.CreateGoal
import com.example.targetsavings.screens.DashboardScreen
import com.example.targetsavings.screens.DepositScreen
import com.example.targetsavings.screens.HomeScreen
import com.example.targetsavings.ui.theme.TargetSavingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TargetSavingsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "dashboard_screen"
                ) {
                    composable("dashboard_screen"){
                        DashboardScreen(navController)
                    }
                    composable("create_goal"){
                        CreateGoal(navController)
                    }
                    composable("deposit_screen"){
                        DepositScreen(navController)
                    }

                }

            }
        }
    }
}