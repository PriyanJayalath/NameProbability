package com.priyan.nameprobability.di

import com.priyan.nameprobability.data.NameProbabilityApi
import com.priyan.nameprobability.main.NameProbabilityRepository
import com.priyan.nameprobability.main.NameProbabilityRepositoryImpl
import com.priyan.nameprobability.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor




@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNameProbabilityApi():NameProbabilityApi{

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        var client : OkHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NameProbabilityApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNameProbabilityImpl(nameProbabilityApi:NameProbabilityApi):NameProbabilityRepository = NameProbabilityRepositoryImpl(nameProbabilityApi)

}