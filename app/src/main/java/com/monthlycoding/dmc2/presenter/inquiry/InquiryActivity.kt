package com.monthlycoding.dmc2.presenter.inquiry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.common.showDefaultSnackBar
import com.monthlycoding.dmc2.common.showDefaultToast
import com.monthlycoding.dmc2.databinding.ActivityInquiryBinding
import com.monthlycoding.domain.model.Inquiry

class InquiryActivity : BindingActivity<ActivityInquiryBinding>(R.layout.activity_inquiry) {

    private val inquiryViewModel: InquiryViewModel by viewModels { InquiryViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInquiryTypeSpinner()
        observeIsSuccessfulPostInquiry()
        setDoneButtonClickListener()
    }

    private fun setDoneButtonClickListener() {
        binding.tvInquiryButtonDone.setOnClickListener {
            if (isValidateInquiry()) {
                inquiryViewModel.postInquiry(
                    Inquiry(
                        content = binding.etInquiryContent.text.toString(),
                        email = binding.etInquiryEmail.text.toString(),
                        inquiryTypeId = InquiryType.findById(binding.spinnerInquiryType.selectedItemPosition).id
                    )
                )
            } else {
                showDefaultSnackBar(it, "빈 칸 없이 모두 기입해주세요.")
            }
        }
    }

    private fun isValidateInquiry() = (!binding.etInquiryEmail.text.isNullOrBlank()
            && !binding.etInquiryContent.text.isNullOrBlank())

    private fun observeIsSuccessfulPostInquiry() {
        inquiryViewModel.isSuccessfulPostInquiry.observe(this) { isSuccess ->
            if (isSuccess) {
                showDefaultToast(this, "문의가 성공적으로 처리되었습니다.")
                finish()
            }
        }
    }

    private fun initInquiryTypeSpinner() {
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
