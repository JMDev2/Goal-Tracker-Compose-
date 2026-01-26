package com.example.targetsavings.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.targetsavings.R
import com.example.targetsavings.data.entity.SavingsGoal
import com.example.targetsavings.ui.components.AppButton
import com.example.targetsavings.ui.components.AppButtonTwo
import com.example.targetsavings.viewModel.SavingsGoalViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    val savingsGoalViewModel: SavingsGoalViewModel = hiltViewModel()

    val goals by savingsGoalViewModel.goals.collectAsState() // collect StateFlow as Compose state

    val listState = rememberLazyListState()
    val goalsCount = goals.size
    val firstVisibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }



    val currentIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val viewportCenter =
                (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            layoutInfo.visibleItemsInfo.minByOrNull { item ->
                kotlin.math.abs(
                    (item.offset + item.size / 2) - viewportCenter
                )
            }?.index ?: 0
        }
    }

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

            if (!goals.isEmpty()) {

                //THE LIST IS NOT EMPTY
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

                        //Display the list here

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(270.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(8.dp)
                            ) {
                                LazyRow(
                                    state = listState,
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    items(goals) { goal ->
                                        GoalItem(
                                            goal = goal,
                                            modifier = Modifier
                                                .fillParentMaxWidth(0.9f)
                                                .height(250.dp),
                                            progressPercent = (goal.currentAmount / goal.targetAmount)
                                                .toFloat()
                                                .coerceIn(0f, 1f),
                                            onDeposit = { /* handle deposit */ },
                                            onWithdraw = { /* handle withdraw */ }
                                        )
                                    }
                                }
                            }

                            // Dots indicators
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                repeat(goals.size) { index ->
                                    val selected = index == currentIndex

                                    Box(
                                        modifier = Modifier
                                            .size(if (selected) 10.dp else 6.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (selected) Color(0xFF7AC143) else Color.LightGray
                                            )
                                    )

                                    if (index != goals.lastIndex) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                    }
                                }
                            }

                        }


                        //the dots

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            // -------- Row 1: Title + Action --------
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Transaction History",
                                    color = Color(0xFF363636),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                Text(
                                    text = "View All",
                                    color = Color(0xFF7AC143),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier.clickable {
                                        navController.navigate("create_goal")
                                    }
                                )
                            }

                            // -------- Row 2: Action buttons --------
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AppButtonTwo(
                                    text = "All",
                                    outlined = true,
                                    backgroundColor = Color(0xFFF5FFE6),
                                    fontWeight = FontWeight.Bold,
                                    textColor = Color(0xFF363636),              // text white for contrast
                                    onClick = {}
                                )

                                AppButtonTwo(
                                    text = "Deposit",
                                    onClick = {},
                                    outlined = true,
                                )

                                AppButtonTwo(
                                    text = "Withdraw",
                                    onClick = {},
                                    outlined = true,
                                )
                            }


                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                AppButtonTwo(
                                    text = "Withdraw",
                                    onClick = {},
                                    outlined = true,
                                )

                            }
                        }
                    }
                }
            }else{
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
                                containerColor = Color.White
                            ),
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


}

@Composable
fun GoalItem(
    goal: SavingsGoal,
    progressPercent: Float = 0f,
    onDeposit: () -> Unit,
    onWithdraw: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(250.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            // Background
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column {

                // Top row: goal title + more icon


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = goal.goalName,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Card(
                        modifier = Modifier.size(24.dp),
                        shape = RoundedCornerShape(50),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More",
                                tint = Color(0xFF363636),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }


                // Goal amount row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = String.format("%.2f", goal.targetAmount),
                        color = Color.White,
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
                        contentDescription = "Hidden",
                        tint = Color(0xFF363636),
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Progress percentage
                Text(
                    text = "${progressPercent.toInt()}%",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )

                LinearProgressIndicator(
                    progress = progressPercent / 100f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .padding(horizontal = 16.dp)
                )

                // Target amount row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Target Amount (KES)",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = String.format("%.2f", goal.targetAmount),
                        color = Color(0xFFCAFF79),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.hidden),
                        contentDescription = "Hidden",
                        tint = Color(0xFF363636),
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Deposit/Withdraw buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),//                                            .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AppButton(
                        text = "Deposit",
                        leadingIcon = Icons.Default.KeyboardArrowUp,
                        backgroundColor = Color(0xFF80BA27),
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

        }
    }
}
