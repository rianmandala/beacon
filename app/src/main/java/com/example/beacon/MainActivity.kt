package com.example.beacon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.beacon.adapter.SectionPagerAdapter
import com.example.beacon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Beacon)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Beacon"
    }
}