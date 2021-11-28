package com.batdaulaptrinh.completlearningenglishapp.ui.home.tab

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.batdaulaptrinh.completlearningenglishapp.R
import com.batdaulaptrinh.completlearningenglishapp.databinding.FragmentTestTabBinding
import com.batdaulaptrinh.completlearningenglishapp.ui.adapter.WordSetRecyclerAdapter
import com.batdaulaptrinh.completlearningenglishapp.ui.adapter.WordTestRecyclerViewAdapter
import com.batdaulaptrinh.completlearningenglishapp.utils.Utils

class TestTabFragment : Fragment() {
    lateinit var binding: FragmentTestTabBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_tab, container, false)

        binding.experiencePointCv.setOnClickListener{
            val dialog = AlertDialog.Builder(requireContext()).setView(R.layout.statistic_experience_dialog).create()
            dialog.show()
        }

        binding.learningDayCv.setOnClickListener{
            val dialog = AlertDialog.Builder(requireContext()).setView(R.layout.statistic_streak_dialog).create()
            dialog.show()
        }
        //TODO faking here
        binding.recyclerView.adapter = WordTestRecyclerViewAdapter(Utils.getWordSet()){
            wordSet->
            Toast.makeText(context,"CLICK AT TEST SET ${wordSet.setNth}",Toast.LENGTH_LONG).show()
        }


        return binding.root

    }
}