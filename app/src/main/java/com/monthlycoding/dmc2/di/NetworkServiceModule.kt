package com.monthlycoding.dmc2.di

import com.monthlycoding.dmc2.data.remote.api.CardNewsService
import com.monthlycoding.dmc2.data.remote.api.FoodRecommendService
import com.monthlycoding.dmc2.data.remote.api.InquiryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {
    @Singleton
    @Provides
    fun provideCardNewsService(retrofit: Retrofit): CardNewsService =
        retrofit.create(CardNewsService::class.java)

    @Singleton
    @Provides
    fun provideFoodRecommendService(retrofit: Retrofit): FoodRecommendService =
        retrofit.create(FoodRecommendService::class.java)

    @Singleton
    @Provides
    fun provideInquiryService(retrofit: Retrofit): InquiryService =
        retrofit.create(InquiryService::class.java)
}
