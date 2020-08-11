package com.example.poqtest.di

import com.example.poqtest.network.IGitRepoService
import com.example.poqtest.utils.GIT_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    fun provideGitApi(): IGitRepoService {
         val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        return Retrofit.Builder()
            .baseUrl(GIT_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(IGitRepoService::class.java)
    }

    single { provideGitApi()}
}
