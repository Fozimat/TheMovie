package com.fozimat.made.themovie.core.domain.usecase

import androidx.lifecycle.LiveData
import com.fozimat.made.themovie.core.data.Resource
import com.fozimat.made.themovie.core.domain.model.Movie

interface MovieUseCase {
    fun getAllMovies() : LiveData<Resource<List<Movie>>>

    fun getFavoriteMovie(): LiveData<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}