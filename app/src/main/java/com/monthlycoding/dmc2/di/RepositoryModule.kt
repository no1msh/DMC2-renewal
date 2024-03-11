package com.monthlycoding.dmc2.di

import com.monthlycoding.dmc2.data.repository.DefaultCardNewsRepository
import com.monthlycoding.dmc2.data.repository.DefaultFoodRecommendRepository
import com.monthlycoding.dmc2.data.repository.DefaultInquiryRepository
import com.monthlycoding.domain.repository.CardNewsRepository
import com.monthlycoding.domain.repository.FoodRecommendRepository
import com.monthlycoding.domain.repository.InquiryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindCardNewsRepository(implement: DefaultCardNewsRepository): CardNewsRepository

    @Singleton
    @Binds
    abstract fun bindFoodRecommendRepository(implement: DefaultFoodRecommendRepository): FoodRecommendRepository

    @Singleton
    @Binds
    abstract fun bindInquiryRepository(implement: DefaultInquiryRepository): InquiryRepository
}
