package com.monthlycoding.dmc2.presenter.schoolaroundmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivitySchoolAroundMapBinding

class SchoolAroundMapActivity :
    BindingActivity<ActivitySchoolAroundMapBinding>(R.layout.activity_school_around_map) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun getIntent(context: Context): Intent =
            Intent(context, SchoolAroundMapActivity::class.java)
    }
}
