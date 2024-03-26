package com.monthlycoding.dmc2.presenter.inquiry

import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.common.showDefaultToast
import com.monthlycoding.dmc2.databinding.ActivityInquiryBinding
import com.monthlycoding.domain.model.Inquiry
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InquiryActivity : BindingActivity<ActivityInquiryBinding>(R.layout.activity_inquiry) {

    private val inquiryViewModel: InquiryViewModel by viewModels()

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
                    ),
                    ::onRemoteError,
                )
            }
        }
    }

    private fun isValidateInquiry(): Boolean {
        if (binding.etInquiryContent.text.isNullOrBlank()) {
            showDefaultToast(this, "빈 칸 없이 모두 기입해주세요.")
            return false
        }
        if (!binding.etInquiryEmail.text.isValidateEmail()) {
            showDefaultToast(this, "이메일 형식이 유효하지 않습니다.")
            return false
        }
        return true
    }

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

    private fun onRemoteError(message: String) {
        showDefaultToast(this, message)
        finish()
    }

    private fun CharSequence?.isValidateEmail() =
        !isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
