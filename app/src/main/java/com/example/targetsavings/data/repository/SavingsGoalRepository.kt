package com.example.targetsavings.data.repository

import com.example.targetsavings.data.entity.SavingsGoal
import com.example.targetsavings.data.room.dao.SavingsGoalDao
import kotlinx.coroutines.flow.Flow

class SavingsGoalRepository(private val dao: SavingsGoalDao) {
    suspend fun insertGoal(goal: SavingsGoal) {
        dao.insertGoal(goal)
    }

    fun getAllGoals(): Flow<List<SavingsGoal>> {
        return dao.getAllGoals()
    }
}
