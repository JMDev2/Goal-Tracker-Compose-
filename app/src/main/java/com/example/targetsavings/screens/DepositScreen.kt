package com.example.targetsavings.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.targetsavings.R
import com.example.targetsavings.data.entity.GoalContribution
import com.example.targetsavings.data.model.creditAccounts
import com.example.targetsavings.ui.components.AppButton
import com.example.targetsavings.ui.components.AppDropdown
import com.example.targetsavings.ui.components.AppOutlinedTextField
import com.example.targetsavings.ui.components.DepositMethodSelector
import com.example.targetsavings.utils.AppTopBar
import com.example.targetsavings.utils.ShowToast
import com.example.targetsavings.viewModel.SavingsGoalViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositScreen(navController: NavHostController) {

    val savingsGoalViewModel: SavingsGoalViewModel = hiltViewModel()
    val goals by savingsGoalViewModel.goals.collectAsState()

    var selectedGoalName by rememberSaveable { mutableStateOf("") }



    val selectedGoal = goals.firstOrNull { it.goalName == selectedGoalName }
    val selectedGoalId = selectedGoal?.id

    var selectedMethod by rememberSaveable {
        mutableStateOf(DepositMethod.ACCOUNT)
    }


    var phoneNumber by remember { mutableStateOf("") }
    var depositAmountMpesa by remember { mutableStateOf("") }
    var depositAmountAccount by remember { mutableStateOf("") }

    var selectedAccount by rememberSaveable { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }


    val context = LocalContext.current


    Scaffold(
        topBar = {
            AppTopBar(
                title = "Deposit",
                navController = navController,
                showBack = true,
                showClose = true
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Goal Name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFC6C6C6)
                )

                Spacer(modifier = Modifier.height(6.dp))

                AppDropdown(
                    categories = goals.map { it.goalName },
                    selectedCategory = selectedGoalName,
                    onCategorySelected = { selectedGoalName = it },
                    placeholder = "Choose a savings goal"
                )

                Text(
                    text = "Display the balance here  ${selectedGoal?.currentAmount ?: 0.0}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFC6C6C6)
                )

                Spacer(modifier = Modifier.height(12.dp))


                Spacer(modifier = Modifier.height(24.dp))

                DepositMethodSelector(
                    selectedMethod = selectedMethod,
                    onMethodSelected = { selectedMethod = it }
                )

                when (selectedMethod) {
                    DepositMethod.ACCOUNT -> {
                        // Show account deposit UI
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Credit Account",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFC6C6C6)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        AppDropdown(
                            categories = creditAccounts.map { "${it.name}\n${it.number}" },
                            selectedCategory = selectedAccount,
                            onCategorySelected = { selectedAccount = it },
                            placeholder = "Choose a credit account",
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.account_img),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Credit Accountvnf",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFC6C6C6)
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Deposit Amount",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFC6C6C6)
                        )

                        AppOutlinedTextField(
                            value = depositAmountAccount,
                            onValueChange = { depositAmountAccount = it },
                            placeholderText = "10,000",
                            prefixText = "Ksh |" //
                        )




                    }
                    DepositMethod.MPESA -> {
                        // Show Mpesa STK push UI
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Phone number",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFC6C6C6),
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        AppOutlinedTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            placeholderText = "0700809279",
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person, // choose any icon you want
                                    contentDescription = "Check",
                                    tint = Color(0xFF707070)          // color of the icon
                                )
                            }
                        )


                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Target Amount",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFC6C6C6)
                        )

                        AppOutlinedTextField(
                            value = depositAmountMpesa,
                            onValueChange = { depositAmountMpesa = it },
                            placeholderText = "10,000",
                            prefixText = "Ksh |" //
                        )

                    }
                }

                AppButton(
                    text = "Save Deposit",
                    onClick = {
                        // Check that a goal is selected
                        if (selectedGoalId != null) {
                            if (selectedGoalId.isBlank()) {
                                ShowToast(context, "Please select a goal")
                                return@AppButton
                            }
                        }

                        // Validate input based on selected method
                        when (selectedMethod) {
                            DepositMethod.ACCOUNT -> {
                                if (selectedAccount.isBlank() || depositAmountAccount.isBlank()) {
                                    ShowToast(context, "Please fill all fields")
                                    return@AppButton
                                }
                            }

                            DepositMethod.MPESA -> {
                                if (phoneNumber.isBlank() || depositAmountMpesa.isBlank()) {
                                    ShowToast(context, "Please fill all fields")
                                    return@AppButton
                                }
                            }
                        }

                        // Determine the deposit amount
                        val amount = when (selectedMethod) {
                            DepositMethod.ACCOUNT -> depositAmountAccount.toDoubleOrNull() ?: 0.0
                            DepositMethod.MPESA -> depositAmountMpesa.toDoubleOrNull() ?: 0.0
                        }

                        if (amount <= 0) {
                            ShowToast(context, "Invalid deposit amount")
                            return@AppButton
                        }

                        // Create contribution object
                        val contribution = GoalContribution(
                            goalId = selectedGoalId,  // Must be non-blank
                            depositMethod = selectedMethod.name,
                            accountNumber = if (selectedMethod == DepositMethod.ACCOUNT) selectedAccount else null,
                            phoneNumber = if (selectedMethod == DepositMethod.MPESA) phoneNumber else null,
                            amount = amount,
                            transactionType = savingsGoalViewModel.transactionType.value,
                            timestamp = System.currentTimeMillis()
                        )

                        // Insert contribution and update goal automatically
                        savingsGoalViewModel.addContribution(contribution)

                        // Show success dialog
                        showSuccessDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )


// Show the dialog
                if (showSuccessDialog) {
                    GoalSavedDialog(
                        goalName = selectedGoalName,
                        onDismiss = { showSuccessDialog = false },
                        onGoToGoals = {
                            showSuccessDialog = false
                            navController.navigate("dashboard_screen") {
                                popUpTo("dashboard_screen") { inclusive = false }
                            }
                        }
                    )
                }




            }
        }
    }
}


enum class DepositMethod {
    ACCOUNT, MPESA
}
