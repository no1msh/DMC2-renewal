package com.monthlycoding.dmc2.custom

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.databinding.CustomViewHitAndMissCardBinding

class HitAndMissCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CustomViewHitAndMissCardBinding by lazy {
        CustomViewHitAndMissCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private var isFlipped: Boolean = false
    private var isHit: Boolean = false
    private val cardBack: ConstraintLayout by lazy { binding.clHitAndMissCardBack }
    private val cardFrontHit: TextView by lazy { binding.tvHitAndMissCardFrontHit }
    private val cardFrontMiss: ConstraintLayout by lazy { binding.clHitAndMissCardFrontMiss }

    init {
        this.cardBack.visibility = View.VISIBLE

        this.setOnClickListener {
            if (this.isFlipped) return@setOnClickListener
            (it as HitAndMissCardView).flipCard()
        }
    }

    fun isHitCard(isHitCard: Boolean) {
        isHit = isHitCard
    }

    fun flipCard() {
        isFlipped = true
        val visibleView = if (isHit) cardFrontHit else cardFrontMiss

        visibleView.visibility = View.VISIBLE
        val scale = context.resources.displayMetrics.density
        val cameraDist = 8000 * scale
        visibleView.cameraDistance = cameraDist
        cardBack.cameraDistance = cameraDist

        val flipOutAnimatorSet =
            AnimatorInflater.loadAnimator(
                context,
                R.animator.flip_out
            ) as AnimatorSet
        flipOutAnimatorSet.setTarget(cardBack)

        val flipInAnimatorSet =
            AnimatorInflater.loadAnimator(
                context,
                R.animator.flip_in
            ) as AnimatorSet

        flipInAnimatorSet.setTarget(visibleView)
        flipOutAnimatorSet.start()
        flipInAnimatorSet.start()
        flipInAnimatorSet.doOnEnd {
            cardBack.visibility = View.GONE
        }
    }

}
