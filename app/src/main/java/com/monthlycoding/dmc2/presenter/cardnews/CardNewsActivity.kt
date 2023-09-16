package com.monthlycoding.dmc2.presenter.cardnews

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class CardNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)
        initWebView(webView)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(webView: WebView) {
        webView.loadUrl(CARD_NEWS_URL)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
    }

    companion object {
        private const val CARD_NEWS_URL = "https://wogur2689.github.io/DMC2_Front/"
    }
}
