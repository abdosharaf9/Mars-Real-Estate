package com.abdosharaf.marsrealestate.network

import com.abdosharaf.marsrealestate.utils.Constants
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("realestate")
    suspend fun getRealEstate(
        @Query("filter") type: String
    ): List<MarsItem>
}

object RetrofitService {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

    val apiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}