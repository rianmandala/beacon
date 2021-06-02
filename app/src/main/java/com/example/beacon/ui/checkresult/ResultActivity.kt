package com.example.beacon.ui.checkresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.beacon.databinding.ActivityResultBinding
import com.example.beacon.utils.Model

class ResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Hasil Pengecekan"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        val intent = intent.getParcelableExtra<Model>(EXTRA_DATA) as Model
        with(intent){
            binding.imageView.setImageResource(image)
            val description =
                "Hasil Pengecekan: \n$description"
            binding.description.text = model.description
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}