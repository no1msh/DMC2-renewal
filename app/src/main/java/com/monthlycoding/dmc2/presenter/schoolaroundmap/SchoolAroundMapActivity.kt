package com.monthlycoding.dmc2.presenter.schoolaroundmap

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.common.showDefaultToast
import com.monthlycoding.dmc2.databinding.ActivitySchoolAroundMapBinding
import com.monthlycoding.dmc2.presenter.foodRecommendDetail.FoodRecommendDetailWebActivity
import com.monthlycoding.dmc2.presenter.schoolaroundmap.model.CuisineMarker
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

class SchoolAroundMapActivity :
    BindingActivity<ActivitySchoolAroundMapBinding>(R.layout.activity_school_around_map),
    MapFilterDialogClickListener,
    MarkerDetailClickListener,
    OnMapReadyCallback {

    private val schoolAroundMapViewModel: SchoolAroundMapViewModel by viewModels { SchoolAroundMapViewModel.Factory }
    private lateinit var naverMap: NaverMap
    private val displayedMarkers: MutableList<Marker> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        initMapFragment()
        observeCuisineMarkers()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSchoolAroundMapToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun initMapFragment() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.fcv_school_around_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.fcv_school_around_map, it).commit()
            }
        mapFragment.getMapAsync(this)
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
        displayedMarkers.forEach { it.map = null }
        displayedMarkers.clear()
        schoolAroundMapViewModel.getStoreMarkers(categoryIds)
    }

    private fun observeCuisineMarkers() {
        schoolAroundMapViewModel.cuisineMarkers.observe(this) { cuisineMarkers ->
            cuisineMarkers.forEach {
                displayedMarkers.add(displayCuisineMarker(it))
            }
        }
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        initMap()
    }

    private fun initMap() {
        val initLatLng = CameraUpdate.scrollTo(SCHOOL_LAT_LNG)
        val initZoomLevel = CameraUpdate.zoomTo(INIT_ZOOM_LEVEL)
        naverMap.moveCamera(initLatLng)
        naverMap.moveCamera(initZoomLevel)

        val marker = Marker()
        marker.position = SCHOOL_LAT_LNG
        marker.map = naverMap
        marker.captionText = "8호관"
        marker.setOnClickListener {
            moveCameraToMaker(it as Marker)
            showDefaultToast(
                this@SchoolAroundMapActivity,
                getString(R.string.marker_school_description)
            )
            return@setOnClickListener true
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val cuisineMarker =
                intent.getParcelableExtra(KEY_CUISINE_MARKER, CuisineMarker::class.java) ?: return
            initNewCuisineMarker(cuisineMarker)
        } else {
            val cuisineMarker =
                intent.getParcelableExtra<CuisineMarker>(KEY_CUISINE_MARKER) ?: return
            initNewCuisineMarker(cuisineMarker)
        }
    }

    private fun initNewCuisineMarker(cuisineMarker: CuisineMarker) {
        Marker().apply {
            position = cuisineMarker.latLng
            map = naverMap
            captionText = cuisineMarker.storeName
            icon = OverlayImage.fromResource(cuisineMarker.icon)
            width = 70
            height = 70
            setOnClickListener {
                moveCameraToMaker(it as Marker)
                showMarkerDetail(cuisineMarker)
                return@setOnClickListener true
            }
        }
        naverMap.moveCamera(CameraUpdate.scrollTo(cuisineMarker.latLng))
    }

    private fun moveCameraToMaker(marker: Marker) {
        val markerPosition = CameraUpdate.scrollTo(marker.position).animate(CameraAnimation.Easing)
        naverMap.moveCamera(markerPosition)
    }

    private fun displayCuisineMarker(cuisineMarker: CuisineMarker): Marker {
        return Marker(cuisineMarker.latLng).apply {
            captionText = cuisineMarker.storeName
            icon = OverlayImage.fromResource(cuisineMarker.icon)
            width = 70
            height = 70
            map = naverMap
            setOnClickListener {
                moveCameraToMaker(it as Marker)
                showMarkerDetail(cuisineMarker)
                return@setOnClickListener true
            }
        }
    }

    private fun showMarkerDetail(cuisineMarker: CuisineMarker) {
        val dialog = MarkerDetailBottomSheetDialog(cuisineMarker, this)
        dialog.show(supportFragmentManager, MarkerDetailBottomSheetDialog.TAG)
    }

    override fun onDetailClick(url: String, storeName: String) {
        startActivity(FoodRecommendDetailWebActivity.getIntent(this, url, storeName))
    }

    companion object {
        private const val DEFAULT_DIALOG_WIDTH = 340
        private const val DEFAULT_DIALOG_HEIGHT = 600
        private const val KEY_CUISINE_MARKER = "KEY_CUISINE_MARKER"
        private val SCHOOL_LAT_LNG = LatLng(37.501015, 126.866547)

        private const val INIT_ZOOM_LEVEL = 16.0

        fun getIntent(context: Context): Intent =
            Intent(context, SchoolAroundMapActivity::class.java)

        fun getIntent(context: Context, cuisineMarker: CuisineMarker): Intent =
            Intent(context, SchoolAroundMapActivity::class.java).apply {
                putExtra(KEY_CUISINE_MARKER, cuisineMarker)
            }
    }
}
