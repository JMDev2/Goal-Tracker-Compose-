package com.example.targetsavings.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deposit_goals")
data class DepositGoal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val goalName: String,
    val depositMethod: String, // "ACCOUNT" or "MPESA"
    val accountNumber: String? = null, // optional, for Account
    val phoneNumber: String? = null,   // optional, for Mpesa
    val amount: Double
)
