package com.monthlycoding.dmc2.presenter.schoolfood

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class SchoolFoodActivity :
    AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)
        initWebView(webView)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(webView: WebView) {
        webView.loadUrl(SCHOOL_FOOD_URL)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
    }

    companion object {
        private const val SCHOOL_FOOD_URL = "https://m.dongyang.ac.kr/dongyang/130/subview.do"

        fun getIntent(context: Context): Intent = Intent(context, SchoolFoodActivity::class.java)
    }
}
