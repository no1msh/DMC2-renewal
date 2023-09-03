package com.monthlycoding.dmc2.presenter.hitandmiss.hitcounter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivityHitCounterBinding
import com.monthlycoding.dmc2.presenter.hitandmiss.HitAndMissActivity

class HitCounterActivity :
    BindingActivity<ActivityHitCounterBinding>(R.layout.activity_hit_counter) {

    private val hitCounterViewModel: HitCounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        bindViewModel()
        setButtonDoneClickListener()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbHitCounterToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun bindViewModel() {
        binding.viewModel = hitCounterViewModel
        binding.lifecycleOwner = this
    }

    private fun setButtonDoneClickListener() {
        binding.tvHitCounterButtonDone.setOnClickListener {
            startActivity(HitAndMissActivity.getIntent(this, hitCounterViewModel.count.value ?: 1))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun getIntent(context: Context): Intent =
            Intent(context, HitCounterActivity::class.java)
    }
}
