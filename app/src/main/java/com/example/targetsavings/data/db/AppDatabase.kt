package com.example.targetsavings.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.targetsavings.data.entity.GoalContribution
import com.example.targetsavings.data.entity.SavingsGoal
import com.example.targetsavings.data.room.dao.GoalContributionDao
import com.example.targetsavings.data.room.dao.SavingsGoalDao

@Database(
    entities = [SavingsGoal::class, GoalContribution::class], // <-- Add GoalContribution here
    version = 4, // increment version when adding a new table
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun savingsGoalDao(): SavingsGoalDao
    abstract fun goalContributionDao(): GoalContributionDao
}
