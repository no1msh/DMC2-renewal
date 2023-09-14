package com.monthlycoding.dmc2.presenter.schoolaroundmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.databinding.DialogMarkerDetailBottomSheetBinding
import com.monthlycoding.dmc2.presenter.schoolaroundmap.model.CuisineMarker

class MarkerDetailBottomSheetDialog(
    private val cuisineMarker: CuisineMarker,
    private val markerDetailClickListener: MarkerDetailClickListener
) :
    BottomSheetDialogFragment() {

    private lateinit var binding: DialogMarkerDetailBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.dialog_marker_detail_bottom_sheet, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.marker = cuisineMarker
        binding.markerDetailClickListener = markerDetailClickListener
    }

    companion object {
        const val TAG = "BasicBottomModalSheet"
    }
}
