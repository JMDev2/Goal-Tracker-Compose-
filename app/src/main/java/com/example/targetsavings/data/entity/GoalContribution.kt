package com.example.targetsavings.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "goal_contributions",
    foreignKeys = [
        ForeignKey(
            entity = SavingsGoal::class,
            parentColumns = ["id"],
            childColumns = ["goalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["goalId"])]
)
data class GoalContribution(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val goalId: String?,
    val depositMethod: String,  // "ACCOUNT" or "MPESA"
    val accountNumber: String? = null,
    val phoneNumber: String? = null,
    val amount: Double,
    val transactionType: String,  // "Deposit" or "Withdraw"
    val timestamp: Long = System.currentTimeMillis()
)
