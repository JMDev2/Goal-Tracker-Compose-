package com.example.targetsavings.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.targetsavings.data.entity.GoalContribution
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalContributionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContribution(contribution: GoalContribution)

    // Fetch contributions for a specific goal, latest first
    @Query("SELECT * FROM goal_contributions WHERE goalId = :goalId ORDER BY timestamp DESC")
    fun getContributionsForGoal(goalId: String): Flow<List<GoalContribution>>

    // Fetch all contributions, latest first
    @Query("SELECT * FROM goal_contributions ORDER BY timestamp DESC")
    fun getAllContributions(): Flow<List<GoalContribution>>

//remove
    @Query("SELECT COUNT(*) FROM goal_contributions")
    suspend fun countContributions(): Int
    @Query("SELECT goalId FROM goal_contributions")
    suspend fun getAllGoalIds(): List<String?>


}
