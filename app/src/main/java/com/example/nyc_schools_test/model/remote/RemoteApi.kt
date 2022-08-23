package com.example.nyc_schools_test.model.remote

import com.example.nyc_schools_test.common.END_POINT
import com.example.nyc_schools_test.model.remote.response.HeroeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApi {

    @GET(END_POINT)
    suspend fun getHeroeList(
        @Path("character-id") characterId: String,
        @Path("access-token") access_token: String = "5537402619638435",
    ): Response<HeroeResponse>


}