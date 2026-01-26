package com.example.targetsavings.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.targetsavings.R
import com.example.targetsavings.ui.components.AppButton

@Composable
fun GoalSavedDialog(
    goalName: String,
    onDismiss: () -> Unit,
    onGoToGoals: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // ✅ Icon
                Image(
                    painter = painterResource(id = R.drawable.success),
                    contentDescription = "Success",
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // ✅ Title
                Text(
                    text = "$goalName Savings Goal",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7AC143),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // ✅ Subtitle
                Text(
                    text = "Created Successfully",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF555454)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // ✅ Description
                Text(
                    text = "You are one step closer to\n reaching your target",
                    fontSize = 16.sp,
                    color = Color(0xFF555454),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ✅ Button
                AppButton(
                    text = "Go to My Goals",
                    onClick = onGoToGoals,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

