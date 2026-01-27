package com.example.targetsavings.data.repository

import com.example.targetsavings.data.entity.GoalContribution
import com.example.targetsavings.data.entity.SavingsGoal
import com.example.targetsavings.data.room.dao.GoalContributionDao
import com.example.targetsavings.data.room.dao.SavingsGoalDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SavingsGoalRepository @Inject constructor(
    private val dao: SavingsGoalDao,
    private val contributionDao: GoalContributionDao
) {

    // Insert a new goal
    suspend fun insertGoal(goal: SavingsGoal) = dao.insertGoal(goal)

    // Fetch all goals as a Flow
    fun getAllGoals(): Flow<List<SavingsGoal>> = dao.getAllGoals()

    // Insert a new contribution
    suspend fun insertContribution(contribution: GoalContribution) =
        contributionDao.insertContribution(contribution)


    fun getAllContributions(): Flow<List<GoalContribution>> = contributionDao.getAllContributions()



    suspend fun addToGoalCurrentAmount(goalId: String, amount: Double) =
        dao.addToCurrentAmount(goalId, amount)
}
