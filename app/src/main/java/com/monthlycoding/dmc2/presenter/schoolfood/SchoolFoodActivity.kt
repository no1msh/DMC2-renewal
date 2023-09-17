package com.monthlycoding.dmc2.presenter.schoolfood

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivitySchoolFoodBinding

class SchoolFoodActivity :
    BindingActivity<ActivitySchoolFoodBinding>(R.layout.activity_school_food) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        initWebView(binding.wbSchoolFoodWeb)
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSchoolFoodToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(webView: WebView) {
        webView.loadUrl(SCHOOL_FOOD_URL)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
    }

    companion object {
        private const val SCHOOL_FOOD_URL = "https://m.dongyang.ac.kr/dongyang/130/subview.do"
    }
}
