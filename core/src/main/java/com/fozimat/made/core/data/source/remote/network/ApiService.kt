package com.fozimat.made.core.data.source.remote.network

import com.fozimat.made.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/trending/movie/day")
    suspend fun getTrendingAllDayMovies(@Query("api_key") apiKey: String): ListMovieResponse
}