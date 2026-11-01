package com.example.proyecto_damian_compose.controller

import com.example.proyecto_damian_compose.api.UserApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import kotlin.jvm.java

import com.example.proyecto_damian_compose.api.FakePeliculaApiService
import com.example.proyecto_damian_compose.api.FakeUserApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request
import java.util.concurrent.TimeUnit


object RetrofitConfig{

    var usandoApiFalsa = true
        private set


    suspend fun comprobarServidor(){
        usandoApiFalsa = !servidorDisponible()
    }


    private suspend fun servidorDisponible(): Boolean = withContext(Dispatchers.IO){
        try{
            val cliente = okHttpClient.newBuilder().callTimeout(60, TimeUnit.SECONDS).build()
            val peticion = Request.Builder().url(BASE_URL + "health").build()
            cliente.newCall(peticion).execute().use{it.isSuccessful}
        } catch (e: Exception){
            false
        }
    }

    private val contentType =
        "application/json; charset=utf-8"
            .toMediaType()

    private val okHttpClient =
        OkHttpClient.Builder()
            .build()

    private const val BASE_URL= "https://moviesrestapi-production.up.railway.app/api/v1/"


    private val json = Json {

        ignoreUnknownKeys = true

    }

    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory(contentType)
            )
            .build()
    }

    fun userApiService(): UserApiService{
        if (usandoApiFalsa) return FakeUserApiService
        return retrofit.create(UserApiService::class.java)
    }

    fun peliculaApiService(): PeliculaApiService{
        if (usandoApiFalsa) return FakePeliculaApiService
        return retrofit.create(PeliculaApiService::class.java)
    }




}