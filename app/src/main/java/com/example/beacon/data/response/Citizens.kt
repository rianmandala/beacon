package com.example.beacon.data.response

data class Citizens(
    var name: String?="",
    var nik: String?="",
    var income:Int?=0,
    var home_ownership_status: Int?=0,
    var number_of_dependents: Int?=0,
    var floor_material: Int?=0,
    var wall_material: Int?=0,
    var building_area: Int?=0,
    var status_social_assistance: Boolean?=false,
)