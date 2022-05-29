package com.priyan.nameprobability.data.models


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country_id")
    val countryId: String,
    @SerializedName("probability")
    val probability: Double
)