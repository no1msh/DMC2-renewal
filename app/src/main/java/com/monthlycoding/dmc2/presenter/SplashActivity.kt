package com.monthlycoding.dmc2.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.presenter.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        lifecycleScope.launch {
            delay(DURATION)
            startActivity(MainActivity.getIntent(this@SplashActivity))
            finish()
        }
    }

    companion object {
        private const val DURATION: Long = 2000
    }
}
