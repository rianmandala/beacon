package com.example.beacon.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Model(
    var id: String,
    var image: Int,
    var description: String
): Parcelable