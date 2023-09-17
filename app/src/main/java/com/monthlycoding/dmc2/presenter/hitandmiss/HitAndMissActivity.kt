package com.monthlycoding.dmc2.presenter.hitandmiss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setMargins
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.custom.HitAndMissCardView
import com.monthlycoding.dmc2.databinding.ActivityHitAndMissBinding

class HitAndMissActivity :
    BindingActivity<ActivityHitAndMissBinding>(R.layout.activity_hit_and_miss) {

    private val hitAndMissViewModel: HitAndMissViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        hitAndMissViewModel.initHitAndMissGame(intent.getIntExtra(KEY_HIT_COUNT, 0))
        initCards()
        setDoneButtonClickListener()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbHitAndMissToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun initCards() {
        hitAndMissViewModel.hitAndMissGame.cards.forEach { hitAndMissCard ->
            binding.glHitAndMissCards.addView(
                createStandardHitAndMissCardView().apply {
                    this.isHitCard(hitAndMissCard.isHit)
                    elevation = 10.0f
                }
            )
        }
    }

    private fun createStandardHitAndMissCardView(): HitAndMissCardView {
        val hitAndMissCard = HitAndMissCardView(this)
        val layoutParams = ConstraintLayout.LayoutParams(
            getDpToPixel(DEFAULT_CUISINE_VIEW_WIDTH),
            getDpToPixel(DEFAULT_CUISINE_VIEW_HEIGHT),
        ).apply { this.setMargins(getDpToPixel(DEFAULT_CUISINE_VIEW_MARGIN)) }

        hitAndMissCard.layoutParams = layoutParams

        return hitAndMissCard
    }

    private fun setDoneButtonClickListener() {
        binding.tvHitAndMissButtonDone.setOnClickListener {
            finish()
        }
    }

    private fun getDpToPixel(dp: Int) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(), this.resources.displayMetrics
    ).toInt()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val KEY_HIT_COUNT = "KEY_HIT_COUNT"
        private const val DEFAULT_CUISINE_VIEW_WIDTH = 120
        private const val DEFAULT_CUISINE_VIEW_HEIGHT = 180
        private const val DEFAULT_CUISINE_VIEW_MARGIN = 4

        fun getIntent(context: Context, hitCount: Int): Intent {
            val intent = Intent(context, HitAndMissActivity::class.java).apply {
                putExtra(KEY_HIT_COUNT, hitCount)
            }
            return intent
        }
    }
}
