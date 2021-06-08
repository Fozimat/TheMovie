package com.fozimat.made.themovie.core.domain.repository

import com.fozimat.made.themovie.core.data.Resource
import com.fozimat.made.themovie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}