package com.monthlycoding.dmc2.presenter.main

import android.os.Bundle
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.common.showDefaultSnackBar
import com.monthlycoding.dmc2.databinding.ActivityMainBinding
import com.monthlycoding.dmc2.presenter.cardnews.CardNewsActivity
import com.monthlycoding.dmc2.presenter.foodrecommend.FoodRecommendActivity
import com.monthlycoding.dmc2.presenter.hitandmiss.hitcounter.HitCounterActivity
import com.monthlycoding.dmc2.presenter.inquiry.InquiryActivity
import com.monthlycoding.dmc2.presenter.schoolaroundmap.SchoolAroundMapActivity
import com.monthlycoding.dmc2.presenter.schoolfood.SchoolFoodActivity

class MainActivity :
    BindingActivity<ActivityMainBinding>(R.layout.activity_main),
    MainClickListener {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.clickListener = this
        mainViewModel.initGreetings()
        observeGreetingsText()
    }

    private fun observeGreetingsText() {
        mainViewModel.greetings.observe(this) {
            binding.tvMainGreetings.text = it.getGreetings(this)
        }
    }

    override fun onFoodRecommendClick() {
        startActivity(FoodRecommendActivity.getIntent(this))
    }

    override fun onHitAndMissGameClick() {
        startActivity(HitCounterActivity.getIntent(this))
    }

    override fun onCardNewsClick() {
        startActivity(CardNewsActivity.getIntent(this))
    }

    override fun onSchoolFoodClick() {
        startActivity(SchoolFoodActivity.getIntent(this))
    }

    override fun onSchoolAroundMapClick() {
        startActivity(SchoolAroundMapActivity.getIntent(this))
    }

    override fun onInquiryClick() {
        startActivity(InquiryActivity.getIntent(this))
    }

    override fun onCommunityClick() {
        // 커뮤니티 화면으로 이동
        showDefaultSnackBar(binding.cvMainCommunity, "준비중인 기능입니다")
    }
}
