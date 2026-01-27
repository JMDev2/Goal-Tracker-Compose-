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

    @Query("SELECT * FROM goal_contributions WHERE goalId = :goalId")
    fun getContributionsForGoal(goalId: String): Flow<List<GoalContribution>>

    @Query("SELECT * FROM goal_contributions")
    fun getAllContributions(): Flow<List<GoalContribution>>
}
