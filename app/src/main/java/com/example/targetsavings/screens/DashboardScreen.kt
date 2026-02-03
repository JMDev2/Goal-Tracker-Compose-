package com.example.targetsavings.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Divider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.targetsavings.data.entity.GoalContribution
import com.example.targetsavings.utils.ShowToast


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController, viewModel: SavingsGoalViewModel = hiltViewModel()
) {
    val savingsGoalViewModel: SavingsGoalViewModel = hiltViewModel()

    val goals by savingsGoalViewModel.goals.collectAsState()
    val contributions by savingsGoalViewModel.contributions.collectAsState()

    var selectedGoalIndex by remember { mutableStateOf(0) } // default first goal
    val selectedGoalId = goals.getOrNull(selectedGoalIndex)?.id

    LaunchedEffect(selectedGoalId) {
        selectedGoalId?.let {
            savingsGoalViewModel.loadContributions(it)
        }
    }

    val listState = rememberLazyListState()
    val context = LocalContext.current

// Transaction type state
    val transactionType by viewModel.transactionType.collectAsState()
    val filterType by viewModel.filterType.collectAsState()


    val currentIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val viewportCenter =
                (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            layoutInfo.visibleItemsInfo.minByOrNull { item ->
                kotlin.math.abs((item.offset + item.size / 2) - viewportCenter)
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

                            var selectedGoalIndex by remember { mutableStateOf(0) } // tracks selected goal

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
                                    itemsIndexed(goals) { index, goal ->   // <-- use itemsIndexed to get the index
                                        GoalItem(
                                            goal = goal,
                                            modifier = Modifier
                                                .fillParentMaxWidth(0.98f)
                                                .height(250.dp)
                                                .clickable { selectedGoalIndex = index }, // <-- clickable here
                                            onDeposit = {
                                                viewModel.setTransactionType("Deposit")
                                                navController.navigate("deposit_screen")
                                            },
                                            onWithdraw = {
                                                viewModel.setTransactionType("Withdraw")
                                                navController.navigate("deposit_screen")
                                            },
                                            viewModel = viewModel
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
                                    onClick = {
                                        viewModel.setFilterType("All")
                                    }
                                )

                                AppButtonTwo(
                                    text = "Deposit",
                                    onClick = {
                                        viewModel.setFilterType("Deposit")
                                    },
                                    outlined = true,
                                )

                                AppButtonTwo(
                                    text = "Withdraw",
                                    onClick = {
                                        viewModel.setFilterType("Withdraw")
                                    },
                                    outlined = true,
                                )
                            }

                            //listing the transactions

                            val filteredContributions = remember(contributions, selectedGoalId, filterType) {
                                contributions
                                    .filter { it.goalId == selectedGoalId }
                                    .filter { contribution ->
                                        when (filterType) {
                                            "Deposit" -> contribution.transactionType == "Deposit"
                                            "Withdraw" -> contribution.transactionType == "Withdraw"
                                            else -> true // "All"
                                        }
                                    }
                            }

                            val emptyMessage = remember(filterType) {
                                when (filterType) {
                                    "Deposit" -> "You have not made Deposits yet"
                                    "Withdraw" -> "You have not made any withdrawals"
                                    else ->"No transactions yet"
                                }
                            }


                            ContributionsSection(
                                contributions = filteredContributions,
                                emptyMessage = emptyMessage,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )

//
                        }
                    }
                }
            } else {
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
    modifier: Modifier = Modifier,
    viewModel: SavingsGoalViewModel

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
                        text = String.format("%.2f", goal.currentAmount),
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

                // Compute progress percentage based on currentAmount and targetAmount
                val progressPercent = if (goal.targetAmount > 0) {
                    (goal.currentAmount / goal.targetAmount * 100).coerceIn(0.0, 100.0)
                } else 0.0

                Text(
                    text = "${progressPercent.toInt()}%",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
                LinearProgressIndicator(
                    progress = (progressPercent / 100).toFloat(), // convert to 0..1 range
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
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
                        .padding(top = 16.dp)
                        .pointerInput(Unit) {},
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AppButton(
                        text = "Deposit",
                        leadingIcon = Icons.Default.KeyboardArrowUp,
                        backgroundColor = Color(0xFF80BA27),
                        onClick = {
                            viewModel.setTransactionType("Deposit")
                            onDeposit()
                        },
                        modifier = Modifier.weight(1f)
                    )

                    AppButton(
                        text = "Withdraw",
                        leadingIcon = Icons.Default.KeyboardArrowDown,
                        textColor = Color(0xFFFFFFFF),  // text visible on transparent bg
                        outlined = true,                 // enable border
                        borderColor = Color.Gray,
                        onClick = {
                            viewModel.setTransactionType("Withdraw")
                            onWithdraw()
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

            }

        }
    }
}


@Composable
fun ContributionsSection(
    contributions: List<GoalContribution>,
    emptyMessage: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (contributions.isEmpty()) {
                Text(
                    text = emptyMessage,
                    color = Color.Black.copy(alpha = 0.7f),
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(contributions) { contribution ->
                        Log.d(
                            "ContributionsList",
                            "Contribution item: ${contribution.id}"
                        )
                        ContributionItem(contribution = contribution)
                    }
                }
            }
        }
    }
}




@Composable
fun ContributionItem(contribution: GoalContribution) {
    val formattedDate = remember(contribution.timestamp) {
        java.text.SimpleDateFormat(
            "dd MMM yyyy, hh:mm a",
            java.util.Locale.getDefault()
        ).format(java.util.Date(contribution.timestamp))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "Contribution",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Account/Phone

            Text(
                text = contribution.accountNumber ?: contribution.phoneNumber ?: "N/A",
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Amount + Date
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "KES ${contribution.amount}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = formattedDate,
                    color = Color.Black.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }
        }

        // Thin divider line below each item
        Divider(
            color = Color(0xFFE0E0E0),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .padding(end = 16.dp)
        )
    }
}




