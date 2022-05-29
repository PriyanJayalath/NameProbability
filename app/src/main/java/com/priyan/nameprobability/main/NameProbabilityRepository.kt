package com.priyan.nameprobability.main

import com.priyan.nameprobability.data.NameProbabilityApi
import com.priyan.nameprobability.data.models.ProbabilityResponse
import retrofit2.Response

interface NameProbabilityRepository {
    suspend fun getData(name:String): Response<ProbabilityResponse>
}