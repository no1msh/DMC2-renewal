package com.monthlycoding.dmc2.presenter.inquiry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivityInquiryBinding

class InquiryActivity : BindingActivity<ActivityInquiryBinding>(R.layout.activity_inquiry) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.spinnerInquiryType.selectedItem

        ArrayAdapter.createFromResource(
            this,
            R.array.inquiry_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerInquiryType.adapter = adapter
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, InquiryActivity::class.java)
    }
}
