package com.example.targetsavings.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.targetsavings.data.entity.SavingsGoal
import com.example.targetsavings.data.room.dao.SavingsGoalDao

@Database(
    entities = [SavingsGoal::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savingsGoalDao(): SavingsGoalDao
}
