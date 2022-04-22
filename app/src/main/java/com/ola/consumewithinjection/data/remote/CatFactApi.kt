package com.ola.consumewithinjection.data.remote

import com.ola.consumewithinjection.data.remote.dto.CatFact
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactApi {

    @GET("/facts/random?animal_type=cat")
    suspend fun getCatFact(@Query("amount") amount: Int): List<CatFact>

    companion object {
        const val BASE_URL = "https://cat-fact.herokuapp.com"
    }
}