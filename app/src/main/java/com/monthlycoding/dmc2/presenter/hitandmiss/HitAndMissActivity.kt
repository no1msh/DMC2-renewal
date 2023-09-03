package com.monthlycoding.dmc2.presenter.hitandmiss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivityHitAndMissBinding

class HitAndMissActivity :
    BindingActivity<ActivityHitAndMissBinding>(R.layout.activity_hit_and_miss) {

    private val hitAndMissViewModel: HitAndMissViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        private const val KEY_HIT_COUNT = "KEY_HIT_COUNT"

        fun getIntent(context: Context, hitCount: Int): Intent {
            val intent = Intent(context, HitAndMissActivity::class.java).apply {
                putExtra(KEY_HIT_COUNT, hitCount)
            }
            return intent
        }
    }
}
