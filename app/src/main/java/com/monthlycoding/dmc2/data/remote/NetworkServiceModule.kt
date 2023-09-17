package com.monthlycoding.dmc2.data.remote

import com.monthlycoding.dmc2.data.remote.api.CardNewsService
import com.monthlycoding.dmc2.data.remote.api.FoodRecommendService
import com.monthlycoding.dmc2.data.remote.api.InquiryService

object NetworkServiceModule {
    val foodRecommendService by lazy { NetworkModule.create<FoodRecommendService>() }
    val inquiryService by lazy { NetworkModule.create<InquiryService>() }
    val cardNewsService by lazy { NetworkModule.create<CardNewsService>() }
}
