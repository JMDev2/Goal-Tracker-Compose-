package com.example.targetsavings.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.targetsavings.R
import com.example.targetsavings.ui.components.AppButton
import com.example.targetsavings.utils.GoalProgressSlider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )

                            Spacer(modifier = Modifier.width(80.dp))

                            Column(
                                verticalArrangement = Arrangement.spacedBy(1.dp)
                            ) {
                                Text(
                                    text = "Hello There",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                )
                                Text(
                                    text = "Its a good day to save",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF272935),
                        titleContentColor = Color.White
                    )
                )
            }
        ) { paddingValues ->
            val context = LocalContext.current

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left text
                        Text(
                            text = "My Goals",
                            color = Color(0xFF272935),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Goal",
                                tint = Color(0xFF7AC143),
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "Add a Goal",
                                color = Color(0xFF7AC143),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable {
                                    navController.navigate("create_goal")
                                }
                            )

                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f) // 90% width of screen
                            .height(250.dp)
                            .clickable {
                                navController.navigate("create_goal")
                            },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            // Background Image
                            Image(
                                painter = painterResource(id = R.drawable.bg),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            Column() {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                    .padding(top = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Left text
                                    Text(
                                        text = "Dubai Trip",
                                        color = Color(0xFFFFFFFF),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.weight(1f))

                                    Card(
                                        modifier = Modifier
                                            .height(22.dp)
                                            .width(22.dp),
                                        shape = RoundedCornerShape(50.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White
                                        ),
                                        elevation = CardDefaults.cardElevation(8.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.MoreVert,
                                                contentDescription = "More",
                                                tint = Color(0xFF363636),
                                                modifier = Modifier.size(12.dp)
                                            )
                                        }

                                    }
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Left text
                                    Text(
                                        text = "900.00",
                                        color = Color(0xFFFFFFFF),
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text(
                                        text = "KES",
                                        color = Color(0xFFCAFF79),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.width(15.dp))

                                    Icon(
                                        painter = painterResource(id = R.drawable.hidden),
                                        contentDescription = "More",
                                        tint = Color(0xFF363636),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }

                            Text(
                                "0.00%",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            )

                                Spacer(modifier = Modifier.height(8.dp))

                                LinearProgressIndicator(
                                    progress = { (100).toFloat() },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                        .padding(start = 16.dp)
                                        .padding(end = 16.dp),
                                )


                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Target Amount (KES)",
                                        color = Color(0xFFFFFFFF),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text(
                                        text = "10,000.00",
                                        color = Color(0xFFCAFF79),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.width(15.dp))

                                    Icon(
                                        painter = painterResource(id = R.drawable.hidden),
                                        contentDescription = "More",
                                        tint = Color(0xFF363636),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }


                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {

                                    // Deposit Button


                                    // Withdraw Button
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),//                                            .padding(horizontal = 16.dp),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        AppButton(
                                            text = "Deposit",
                                            leadingIcon = Icons.Default.KeyboardArrowUp,
                                            backgroundColor = Color(0xFF7AC143),
                                            onClick = { /* Deposit action */ },
                                            modifier = Modifier.weight(1f)
                                        )

                                        AppButton(
                                            text = "Withdraw",
                                            leadingIcon = Icons.Default.KeyboardArrowDown,
                                            textColor = Color(0xFFFFFFFF),  // text visible on transparent bg
                                            outlined = true,                 // enable border
                                            borderColor = Color.Gray,
                                            onClick = { /* Withdraw action */ },
                                            modifier = Modifier.weight(1f)
                                        )

                                    }

                                }


                            }}}


                }
            }

        }
    }
}