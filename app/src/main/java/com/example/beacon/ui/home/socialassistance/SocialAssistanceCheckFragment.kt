package com.example.beacon.ui.home.socialassistance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.beacon.R
import com.example.beacon.databinding.FragmentSocialAssistanceCheckBinding
import com.example.beacon.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class SocialAssistanceCheckFragment : Fragment() {
    private lateinit var binding: FragmentSocialAssistanceCheckBinding
    private val socialAssistanceCheckViewModel: SocialAssistanceCheckViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSocialAssistanceCheckBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        socialAssistanceCheckViewModel.citizens.observe(requireActivity()) { citizens->
            if(citizens?.social_assistance_status == 1 && binding.edtNik.text.toString() == citizens.nik){
                MaterialAlertDialogBuilder(requireActivity())
                    .setTitle(getString(R.string.result))
                    .setMessage(getString(R.string.social_asistance_template_result, citizens.nik))
                    .setPositiveButton(getString(R.string.ok)){ _, _ ->  }
                    .show()
            }else{
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.citizen_not_found, binding.edtNik.text.toString()),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btnSocialAssistanceCheck.setOnClickListener {
            val nik = binding.edtNik.text.toString()
            if(nik.isNotEmpty() && nik.isNotBlank()){
                socialAssistanceCheckViewModel.getCitizenByNik(nik)
            }else{
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.please_input_nik),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}