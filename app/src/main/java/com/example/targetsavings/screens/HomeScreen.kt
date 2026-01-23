package com.example.targetsavings.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.targetsavings.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
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
                    // Top Text
                    Text(
                        text = "Start Saving Towards Your Goals",
                        color = Color(0xFF272935),
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    // Card
                    //navigate to create a goal
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f) // 90% width of screen
                            .height(150.dp)
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
                                painter = painterResource(id = R.drawable.img_1),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            // Column of texts aligned top start
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.CenterStart),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Goal Savings",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(6.dp))


                                Text(
                                    text = "Turn your goals into\n savings!",
                                    fontSize = 14.sp,
                                    color = Color.White
                                )

                            }

                            // Image at bottom end
                            Image(
                                painter = painterResource(id = R.drawable.img_2), // your image
                                contentDescription = "Saving Illustration",
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(90.dp)
                                    .align(Alignment.BottomEnd)

                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))


                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(90.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        // Box to fill card content
                        Box(modifier = Modifier.fillMaxSize()) {
                            // Horizontal scroll row
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .horizontalScroll(rememberScrollState()) // makes it scrollable
                                    .padding(end = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp), // spacing between images
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // First image
                                Image(
                                    painter = painterResource(id = R.drawable.img_3),
                                    contentDescription = "Image 1",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )

                                // Second image
                                Image(
                                    painter = painterResource(id = R.drawable.img_4),
                                    contentDescription = "Image 2",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )

                                // You can add more images here if needed
                            }
                        }
                    }


                }
            }


        }
    }
}

