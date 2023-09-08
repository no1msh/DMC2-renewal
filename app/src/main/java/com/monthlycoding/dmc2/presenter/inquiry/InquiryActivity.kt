package com.monthlycoding.dmc2.presenter.inquiry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivityInquiryBinding

class InquiryActivity : BindingActivity<ActivityInquiryBinding>(R.layout.activity_inquiry) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, InquiryActivity::class.java)
    }
}
