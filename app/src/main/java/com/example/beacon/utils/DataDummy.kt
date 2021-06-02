package com.example.beacon.utils

import com.example.beacon.R

object DataDummy {
    fun generateDummyData(): ArrayList<Model> {
        val data = ArrayList<Model>()

        data.add(Model(
            "1",
            R.drawable.img1,
            "Selamat, berdasarkan kriteria-kriteria yang anda kirimkan, anda termasuk kriteria yang layak mendapatkan bantuan pangan non tunai."
        ))

        data.add(Model(
            "2",
            R.drawable.img2,
            "Mohon maaf, berdasarkan kriteria-kriteria yang anda kirimkan, anda tidak termasuk kriteria yang layak mendapatkan bantuan pangan non tunai."
        ))
        return data
    }
}