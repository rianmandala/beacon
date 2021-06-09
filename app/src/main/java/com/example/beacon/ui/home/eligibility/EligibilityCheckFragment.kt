package com.example.beacon.ui.home.eligibility

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.beacon.R
import com.example.beacon.data.response.Citizens
import com.example.beacon.databinding.FragmentEligibilityCheckBinding
import com.example.beacon.ml.ConvertedModel
import com.example.beacon.ui.checkresult.ResultActivity
import com.google.firebase.ml.modeldownloader.CustomModel
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.math.RoundingMode
import java.nio.ByteBuffer
import java.nio.ByteOrder


class EligibilityCheckFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentEligibilityCheckBinding
    private var interpreter: Interpreter?=null
    private var dataCitizens= Citizens()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEligibilityCheckBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numberOfDependentsItems = listOf("Kurang dari 3 orang", "4 orang", "5 orang", "Lebih dari 6 orang")
        val incomeItems = listOf("Lebih dari 1 juta", "700 - 1 juta", "400 - 700 ribu", "Kurang dari 400 ribu")
        val homeStatusItems = listOf("Milik sendiri", "Milik orang tua", "Sewa kurang dari 1 juta", "Magersari")
        val floorMaterialItems = listOf("Keramik", "Tegel", "Lantai cor", "Tanah")
        val wallMaterialItems = listOf("Tembok kualitas baik", "Tembok biasa ", "Papan kayu biasa", "Bambu")
        val buildingAreaItems = listOf("Lebih dari 100 m2", "75 - 100 m2 ", "50 - 75 m2", "Kurang dari 50 m2")
        binding.numberOfDependents.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, numberOfDependentsItems))
        binding.numberOfDependents.setOnItemClickListener { parent, view, position, id ->
            dataCitizens.number_of_dependents=position+1
        }

        binding.income.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, incomeItems))
        binding.income.setOnItemClickListener { parent, view, position, id ->
            dataCitizens.income=position+1
        }

        binding.homeStatus.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, homeStatusItems))
        binding.homeStatus.setOnItemClickListener { parent, view, position, id ->
            dataCitizens.home_ownership_status=position+1
        }

        binding.floorMaterial.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, floorMaterialItems))
        binding.floorMaterial.setOnItemClickListener { parent, view, position, id ->
            dataCitizens.floor_material=position+1
        }

        binding.wallMaterial.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, wallMaterialItems))
        binding.wallMaterial.setOnItemClickListener { parent, view, position, id ->
            dataCitizens.wall_material=position+1
        }

        binding.buildingArea.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, buildingAreaItems))
        binding.buildingArea.setOnItemClickListener { parent, view, position, id ->
            dataCitizens.building_area=position+1
        }



//        val conditions = CustomModelDownloadConditions.Builder()
//            .requireWifi()  // Also possible: .requireCharging() and .requireDeviceIdle()
//            .build()
//        FirebaseModelDownloader.getInstance()
//            .getModel("cek_bansos", DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
//                conditions)
//            .addOnSuccessListener { model: CustomModel? ->
//                // Download complete. Depending on your app, you could enable the ML
//                // feature, or switch from the local model to the remote model, etc.
//
//                // The CustomModel object contains the local path of the model file,
//                // which you can use to instantiate a TensorFlow Lite interpreter.
//                val modelFile = model?.file
//                if (modelFile != null) {
//                    interpreter = Interpreter(modelFile)
//                }
//            }
        binding.btnCek.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnCek.id -> {
//                val input = ByteBuffer.allocateDirect(6).order(ByteOrder.nativeOrder())
//                val bufferSize = 2 * java.lang.Float.SIZE / java.lang.Byte.SIZE
//                val modelOutput = ByteBuffer.allocateDirect(bufferSize).order(ByteOrder.nativeOrder())
//                interpreter?.run(input, modelOutput)

                if(dataCitizens.income != 0 && dataCitizens.home_ownership_status!=0 && dataCitizens.number_of_dependents!=0
                    && dataCitizens.floor_material!=0 && dataCitizens.wall_material!=0 && dataCitizens.building_area!=0){
                    CoroutineScope(Dispatchers.IO).launch {
                        val conditions = CustomModelDownloadConditions.Builder()
                            .requireWifi()
                            .build()
                        FirebaseModelDownloader.getInstance()
                            .getModel("cek_bansos", DownloadType.LOCAL_MODEL, conditions)
                            .addOnCompleteListener { customModel ->
                                customModel
                                    .addOnSuccessListener { model: CustomModel? ->
                                        val modelFile = model?.file
                                        if (modelFile != null) {
                                        }
                                        val interpreter: Interpreter = modelFile?.let { Interpreter(it) }!!

                                        val input = ByteBuffer.allocateDirect(6 * 4).order(ByteOrder.nativeOrder())
//                        [1,1,1,1,0,1,1,1,0,0,0,1,0,0,0,1,1]
                                        //hasil positif
                                        input.putFloat(dataCitizens.number_of_dependents!!.toFloat())
                                        input.putFloat(dataCitizens.income!!.toFloat())
                                        input.putFloat(dataCitizens.home_ownership_status!!.toFloat())
                                        input.putFloat(dataCitizens.floor_material!!.toFloat())
                                        input.putFloat(dataCitizens.wall_material!!.toFloat())
                                        input.putFloat(dataCitizens.building_area!!.toFloat())

                                        val modelOutput =
                                            ByteBuffer.allocateDirect(2 * java.lang.Float.SIZE / java.lang.Byte.SIZE).order(ByteOrder.nativeOrder())
                                        interpreter.run(input, modelOutput)


                                        modelOutput.rewind()
                                        val probabilities = modelOutput.asFloatBuffer()
                                        try {
                                            val result = probabilities.get(0).toString().substring(2,4)
                                            val moveIntent = Intent(activity, ResultActivity::class.java)
                                            if(result.toInt()>60){
                                                moveIntent.putExtra(ResultActivity.EXTRA_DATA, false)
                                                startActivity(moveIntent)
                                            }else{
                                                moveIntent.putExtra(ResultActivity.EXTRA_DATA, true)
                                                startActivity(moveIntent)
                                            }
                                        } catch (e: IOException) {
                                            Log.d("hasil",e.message.toString())
                                        }
                                    }

                            }
                    }
                } else{
                    Toast.makeText(requireActivity(),"Harap isi semua kriteria", Toast.LENGTH_SHORT).show()
                }
                            }
        }
    }
}