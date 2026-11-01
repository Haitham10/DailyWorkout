package com.haitham.dailyworkout.di

import com.haitham.dailyworkout.data.repository.SampleWorkoutRepository
import com.haitham.dailyworkout.domain.repository.WorkoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWorkoutRepository(
        sampleWorkoutRepository: SampleWorkoutRepository
    ): WorkoutRepository
}