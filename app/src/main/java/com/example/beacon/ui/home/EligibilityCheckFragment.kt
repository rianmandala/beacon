package com.example.beacon.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beacon.R
import com.example.beacon.databinding.FragmentEligibilityCheckBinding

class EligibilityCheckFragment : Fragment() {
    private lateinit var binding: FragmentEligibilityCheckBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEligibilityCheckBinding.inflate(inflater)
        return binding.root
    }
}