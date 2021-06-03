package com.example.beacon.ui.checkresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.beacon.R
import com.example.beacon.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Beacon)
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Hasil Pengecekan"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        val status = intent.getBooleanExtra(EXTRA_DATA,true)
        if(status){
            with(binding){
                imageView.setImageResource(R.drawable.img1)
                progressBarDetail.visibility= View.GONE
                description.text = getString(R.string.pass_criteria,"termasuk")
            }
        }else{
            with(binding){
                imageView.setImageResource(R.drawable.img2)
                progressBarDetail.visibility= View.GONE
                description.text = getString(R.string.pass_criteria,"tidak termasuk")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}