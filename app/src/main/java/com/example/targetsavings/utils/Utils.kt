package com.example.targetsavings.utils


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Show a toast from anywhere
fun ShowToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

// Optional: Compose-friendly version
//@Composable
//fun ShowToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
//    val context = LocalContext.current.applicationContext
//    showToast(context, message, duration)
//}
