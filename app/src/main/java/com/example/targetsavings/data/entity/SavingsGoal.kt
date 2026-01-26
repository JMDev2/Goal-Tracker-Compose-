package com.example.targetsavings.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "goal")
data class SavingsGoal(
    @PrimaryKey val id: String,
    val goalName: String,
    val targetCategory: String,
    val targetAmount: Double,
    val currentAmount: Double = 0.0, // new field
    val targetDate: String
)