package com.monthlycoding.dmc2.presenter.foodRecommendDetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.common.showDefaultToast
import com.monthlycoding.dmc2.databinding.ActivityFoodRecommendDetailWebBinding

class FoodRecommendDetailWebActivity :
    BindingActivity<ActivityFoodRecommendDetailWebBinding>(R.layout.activity_food_recommend_detail_web) {

    private val url: String by lazy { intent.getStringExtra(KEY_WEB_URL) ?: NULL_URL }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        validateUrl()
        initWebView(binding.wvFoodRecommendDetailWebWebView)
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbFoodRecommendDetailWebToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.title = intent.getStringExtra(KEY_APP_BAR_TITLE)
    }

    private fun validateUrl() {
        if (url == NULL_URL) finishWithMessage()
    }

    private fun finishWithMessage() {
        showDefaultToast(
            this,
            getString(R.string.food_recommend_detail_web_exception_finish_message)
        )
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(webView: WebView) {
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                webView.loadUrl(formatUrlNaverMapMobileDetail(request?.url.toString()))
                return false
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.useWideViewPort = true
        webView.settings.setSupportZoom(false)
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = true
        webView.loadUrl(url)
    }

    private fun formatUrlNaverMapMobileDetail(redirectedUrl: String): String {
        return redirectedUrl.replace("share?id=", "restaurant/")
            .replace("&tabsPath=%2Fhome&appMode=detail", "/home")
    }

    private fun handleUrl(uri: Uri): Boolean {
        return !uri.scheme?.startsWith("https")!!
    }

    companion object {
        private const val KEY_WEB_URL = "KEY_WEB_URL"
        private const val KEY_APP_BAR_TITLE = "KEY_APP_BAR_TITLE"
        private const val NULL_URL = "NULL_URL"

        fun getIntent(context: Context, url: String, appBarTitle: String): Intent {
            val intent = Intent(context, FoodRecommendDetailWebActivity::class.java).apply {
                putExtra(KEY_WEB_URL, url)
                putExtra(KEY_APP_BAR_TITLE, appBarTitle)
            }
            return intent
        }
    }
}
