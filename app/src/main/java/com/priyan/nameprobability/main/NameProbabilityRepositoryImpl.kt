package com.priyan.nameprobability.main

import com.priyan.nameprobability.data.NameProbabilityApi
import com.priyan.nameprobability.data.models.ProbabilityResponse
import retrofit2.Response
import javax.inject.Inject

class NameProbabilityRepositoryImpl @Inject constructor(
    private val nameProbabilityApi: NameProbabilityApi
    ):NameProbabilityRepository {

    override suspend fun getData(name:String): Response<ProbabilityResponse> {
        return nameProbabilityApi.getData(name)
    }
}