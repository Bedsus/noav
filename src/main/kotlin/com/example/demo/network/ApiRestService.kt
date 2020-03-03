package com.example.demo.network

import com.example.demo.module.tableMember.Dogs
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiRestService {

    @GET("api/breed/hound/images")
    fun getDogsAsync(): Deferred<Dogs>

}