package com.monthlycoding.dmc2.presenter.main

import android.os.Bundle
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.common.showDefaultSnackBar
import com.monthlycoding.dmc2.databinding.ActivityMainBinding
import com.monthlycoding.dmc2.presenter.foodrecommend.FoodRecommendActivity
import com.monthlycoding.dmc2.presenter.hitandmiss.hitcounter.HitCounterActivity
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
        // 카드뉴스 화면으로 이동
        showDefaultSnackBar(binding.cvMainCardNews, "준비중인 기능입니다")
    }

    override fun onSchoolFoodClick() {
        startActivity(SchoolFoodActivity.getIntent(this))
    }

    override fun onSchoolAroundMapClick() {
        // 지도 화면으로 이동
        showDefaultSnackBar(binding.cvMainSchoolAroundMap, "준비중인 기능입니다")
    }

    override fun onInquireClick() {
        // 문의하기 웹 뷰 인텐트
    }

    override fun onCommunityClick() {
        // 커뮤니티 화면으로 이동
        showDefaultSnackBar(binding.cvMainCommunity, "준비중인 기능입니다")
    }
}
