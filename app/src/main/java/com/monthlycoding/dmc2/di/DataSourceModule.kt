package com.monthlycoding.dmc2.di

import com.monthlycoding.dmc2.data.datasource.remote.CardNewsDataSource
import com.monthlycoding.dmc2.data.datasource.remote.FoodRecommendDataSource
import com.monthlycoding.dmc2.data.datasource.remote.InquiryDataSource
import com.monthlycoding.dmc2.data.datasource.remote.NetworkCardNewsDataSource
import com.monthlycoding.dmc2.data.datasource.remote.NetworkFoodRecommendDataSource
import com.monthlycoding.dmc2.data.datasource.remote.NetworkInquiryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindDefaultCardNewsDataSource(implement: NetworkCardNewsDataSource): CardNewsDataSource

    @Singleton
    @Binds
    abstract fun bindDefaultFoodRecommendDataSource(implement: NetworkFoodRecommendDataSource): FoodRecommendDataSource

    @Singleton
    @Binds
    abstract fun bindDefaultInquiryDataSource(implement: NetworkInquiryDataSource): InquiryDataSource
}
