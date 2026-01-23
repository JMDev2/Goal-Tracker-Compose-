package com.example.targetsavings.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "goal")
data class SavingsGoal(
    @PrimaryKey val name: String,
    val targetAmount: Double,
    val targetDate: String
)