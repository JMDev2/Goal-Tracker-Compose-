package com.example.targetsavings.screens

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.platform.LocalContext

import androidx.navigation.NavHostController
import com.example.targetsavings.ui.components.AppButton
import com.example.targetsavings.ui.components.AppOutlinedTextField
import com.example.targetsavings.ui.components.DateInputField
import com.example.targetsavings.ui.components.GoalCategoryDropdown
import com.example.targetsavings.utils.ShowToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGoal(navController: NavHostController) {

    var goalName by remember { mutableStateOf("") }
    var goalCategory by remember { mutableStateOf("") }
    var targetAmount by remember { mutableStateOf("") }
    var goalDate by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Create a Goal",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cancel",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF272935)
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally //👈 centers children horizontally
            ) {
                Text(
                    text = "Please let’s have the following:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF272935)
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    // All your form fields here
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Goal Name",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFC6C6C6)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    AppOutlinedTextField(
                        value = goalName,
                        onValueChange = { goalName = it },
                        placeholderText = "Dubai Trip"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Goal Category",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFC6C6C6)
                    )
                    GoalCategoryDropdown(
                        selectedCategory = goalCategory,
                        onCategorySelected = { goalCategory = it }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Target Amount",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFC6C6C6)
                    )

                    AppOutlinedTextField(
                        value = targetAmount,
                        onValueChange = { targetAmount = it },
                        placeholderText = "10,000",
                        prefixText = "Ksh |" //
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        text = "Goal Date",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFC6C6C6)
                    )

                    DateInputField(
                        selectedDate = goalDate,
                        onDateSelected = { goalDate = it },
                        placeholderText = "23/02/2026"
                    )
                }

                AppButton(
                    text = "Save Goal",
                    onClick = {
                        if (goalName.isEmpty() || goalCategory.isEmpty() || targetAmount.isEmpty() || goalDate.isEmpty()) {
                            navController.navigate("dashboard_screen")


                        }else{
                            ShowToast(context,"Goal saved successfully!")

                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                )
            }

        }
    }
}

