package com.monthlycoding.dmc2.presenter.main

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivityMainBinding
import com.monthlycoding.dmc2.presenter.schoolfood.SchoolFoodActivity

class MainActivity :
    BindingActivity<ActivityMainBinding>(R.layout.activity_main),
    MainClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.clickListener = this
    }

    override fun onFoodRecommendClick() {
        // 음식 추천 화면으로 이동
        showSnackBar(binding.cvMainFoodRecommend, "준비중인 기능입니다")
    }

    override fun onHitAndMissGameClick() {
        showSnackBar(binding.cvMainHitAndMissGame, "준비중인 기능입니다")
    }

    override fun onCardNewsClick() {
        // 카드뉴스 화면으로 이동
        showSnackBar(binding.cvMainCardNews, "준비중인 기능입니다")
    }

    override fun onSchoolFoodClick() {
        startActivity(SchoolFoodActivity.getIntent(this))
    }

    override fun onSchoolAroundMapClick() {
        // 지도 화면으로 이동
        showSnackBar(binding.cvMainSchoolAroundMap, "준비중인 기능입니다")
    }

    override fun onInquireClick() {
        // 문의하기 웹 뷰 인텐트
    }

    override fun onCommunityClick() {
        // 커뮤니티 화면으로 이동
        showSnackBar(binding.cvMainCommunity, "준비중인 기능입니다")
    }

    private fun showSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
    }
}
