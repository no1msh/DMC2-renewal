package com.monthlycoding.dmc2.presenter.schoolaroundmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.common.showDefaultToast
import com.monthlycoding.dmc2.databinding.ActivitySchoolAroundMapBinding

class SchoolAroundMapActivity :
    BindingActivity<ActivitySchoolAroundMapBinding>(R.layout.activity_school_around_map),
    MapFilterDialogClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSchoolAroundMapToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        menu?.findItem(R.id.filter)?.actionView?.let { view ->
            view.setOnClickListener { showFilterDialog() }
        }
        return true
    }

    private fun showFilterDialog() {
        MapFilterDialog(this, this).apply {
            val density = resources.displayMetrics.density * 1.2
            window?.setLayout(
                (DEFAULT_DIALOG_WIDTH * density).toInt(),
                (DEFAULT_DIALOG_HEIGHT * density).toInt()
            )
            show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCategorySelectDoneClick(categoryIds: List<Int>) {
        showDefaultToast(this, "카테고리 $categoryIds")
    }

    companion object {
        private const val DEFAULT_DIALOG_WIDTH = 340
        private const val DEFAULT_DIALOG_HEIGHT = 600

        fun getIntent(context: Context): Intent =
            Intent(context, SchoolAroundMapActivity::class.java)
    }
}
