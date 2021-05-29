package com.example.beacon.ui.home

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.beacon.R
import com.example.beacon.databinding.FragmentSocialAssistanceCheckBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SocialAssistanceCheckFragment : Fragment() {
   private lateinit var binding: FragmentSocialAssistanceCheckBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSocialAssistanceCheckBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSocialAssistanceCheck.setOnClickListener {
            val nik = binding.edtNik.text.toString()
            if(nik.isNotEmpty() && nik.isNotBlank()){
                MaterialAlertDialogBuilder(requireActivity())
                    .setTitle(getString(R.string.result))
                    .setMessage(getString(R.string.social_asistance_template_result,nik))
                    .setPositiveButton(getString(R.string.ok)){ _, _ ->  }
                    .show()
            }else{
                Toast.makeText(requireActivity(),getString(R.string.please_input_nik),Toast.LENGTH_SHORT).show()
            }
        }
    }
}