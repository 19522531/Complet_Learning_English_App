package com.batdaulaptrinh.completlearningenglishapp.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.batdaulaptrinh.completlearningenglishapp.R

class CustomDialogFragment: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_custom_dialog, container,false)
        return rootView

    }
    }