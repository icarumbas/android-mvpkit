package com.tamimattafi.mvp.repositories.api.server.client

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DynamicClient {

    @GET
    fun get(@Url url: String): Call<ResponseBody>

    @PUT
    fun put(@Url url: String, @Body any: Any): Call<ResponseBody>

    @PUT
    fun put(@Url url: String): Call<ResponseBody>

    @POST
    fun post(@Url url: String, @Body any: Any): Call<ResponseBody>

    @POST
    fun post(@Url url: String): Call<ResponseBody>

    @DELETE
    fun delete(@Url url: String): Call<ResponseBody>

}