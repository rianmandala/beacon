package com.example.beacon.ui.home.eligibility

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beacon.databinding.FragmentEligibilityCheckBinding
import com.example.beacon.ui.checkresult.ResultActivity

class EligibilityCheckFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentEligibilityCheckBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEligibilityCheckBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCek.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnCek.id -> {
                val moveIntent = Intent(activity, ResultActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}