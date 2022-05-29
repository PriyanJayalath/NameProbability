package com.priyan.nameprobability.data.models


import com.google.gson.annotations.SerializedName

data class ProbabilityResponse(
    @SerializedName("country")
    val country: List<Country>,
    @SerializedName("name")
    val name: String
)