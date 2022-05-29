package com.priyan.nameprobability.data

import com.priyan.nameprobability.data.models.ProbabilityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NameProbabilityApi {

    @GET("?")
    suspend fun getData(@Query("name") name:String):Response<ProbabilityResponse>

}