package com.example.poptestluis.network

import com.example.poptestluis.utils.GIT_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofitPupil(): IGitRepoService {


        return Retrofit.Builder()
            .baseUrl(GIT_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(IGitRepoService::class.java)
    }
}