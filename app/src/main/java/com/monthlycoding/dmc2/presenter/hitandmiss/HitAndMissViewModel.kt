package com.monthlycoding.dmc2.presenter.hitandmiss

import androidx.lifecycle.ViewModel
import com.monthlycoding.domain.hitandmiss.HitAndMissGame

class HitAndMissViewModel: ViewModel() {

    lateinit var hitAndMissGame: HitAndMissGame

    fun initHitAndMissGame(hitCount: Int){
        hitAndMissGame = HitAndMissGame(hitCount)
    }

}
