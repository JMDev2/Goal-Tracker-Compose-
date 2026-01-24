package com.example.targetsavings.utils


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@Composable
fun GoalProgressSlider(
    savedAmount: Float,
    targetAmount: Float,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF7AC143),
    inactiveColor: Color = Color(0xFFE0E0E0)
) {
    val progress = remember(savedAmount, targetAmount) {
        if (targetAmount == 0f) 0f
        else ((savedAmount / targetAmount) * 100f).coerceIn(0f, 100f)
    }

    Column(modifier = modifier) {

        // Percentage label
        Text(
            text = "${progress.toInt()}%",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF272935),
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Slider(
            value = progress,
            onValueChange = {}, // 🚫 disabled interaction
            valueRange = 0f..100f,
            enabled = false, // makes it non-interactive
            colors = SliderDefaults.colors(
                disabledThumbColor = activeColor,
                disabledActiveTrackColor = activeColor,
                disabledInactiveTrackColor = inactiveColor
            )
        )
    }
}
