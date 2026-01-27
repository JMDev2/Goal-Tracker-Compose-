package com.example.targetsavings.di

import android.content.Context
import androidx.room.Room
import com.example.targetsavings.data.db.AppDatabase
import com.example.targetsavings.data.db.DatabaseProvider
import com.example.targetsavings.data.repository.SavingsGoalRepository
import com.example.targetsavings.data.room.dao.GoalContributionDao
import com.example.targetsavings.data.room.dao.SavingsGoalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "target_savings_db"
        ).build()
    }

    @Provides
    fun provideSavingsGoalDao(db: AppDatabase): SavingsGoalDao = db.savingsGoalDao()

    @Provides
    fun provideGoalContributionDao(db: AppDatabase): GoalContributionDao = db.goalContributionDao()

    @Provides
    @Singleton
    fun provideSavingsGoalRepository(
        savingsGoalDao: SavingsGoalDao,
        contributionDao: GoalContributionDao
    ): SavingsGoalRepository {
        return SavingsGoalRepository(savingsGoalDao, contributionDao)
    }
}
