package com.fozimat.made.themovie.core.data.source.remote.network

import com.fozimat.made.themovie.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/trending/movie/day")
    fun getTrendingAllDayMovies(@Query("api_key") apiKey: String): Call<ListMovieResponse>
}