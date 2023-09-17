package com.monthlycoding.dmc2.presenter.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivityMainBinding
import com.monthlycoding.dmc2.presenter.cardnews.CardNewsActivity
import com.monthlycoding.dmc2.presenter.foodrecommend.FoodRecommendActivity
import com.monthlycoding.dmc2.presenter.hitandmiss.hitcounter.HitCounterActivity
import com.monthlycoding.dmc2.presenter.inquiry.InquiryActivity
import com.monthlycoding.dmc2.presenter.schoolaroundmap.SchoolAroundMapActivity
import com.monthlycoding.dmc2.presenter.schoolfood.SchoolFoodActivity
import kotlin.reflect.KClass

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
        navigateToActivity(FoodRecommendActivity::class)
    }

    override fun onHitAndMissGameClick() {
        navigateToActivity(HitCounterActivity::class)
    }

    override fun onCardNewsClick() {
        navigateToActivity(CardNewsActivity::class)
    }

    override fun onSchoolFoodClick() {
        navigateToActivity(SchoolFoodActivity::class)
    }

    override fun onSchoolAroundMapClick() {
        navigateToActivity(SchoolAroundMapActivity::class)
    }

    override fun onInquiryClick() {
        navigateToActivity(InquiryActivity::class)
    }

    override fun onCommunityClick() {
        PrepareUpdateDialog().show(supportFragmentManager, PREPARE_FOR_UPDATE_DIALOG_TAG)
    }

    override fun onRecruitClick() {
        val uri = Uri.parse(RECRUIT_URL)
        val viewIntent = Intent(Intent.ACTION_VIEW, uri).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(viewIntent)
    }

    private fun navigateToActivity(clazz: KClass<*>) {
        val intent = Intent(this, clazz.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(intent)
    }

    companion object {
        private const val RECRUIT_URL = "https://kangminna.github.io/MonthlyCoding_Web/"
        private const val PREPARE_FOR_UPDATE_DIALOG_TAG = "prepareForUpdateDialog"

        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
