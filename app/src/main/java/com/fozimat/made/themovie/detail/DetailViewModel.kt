package com.fozimat.made.themovie.detail

import androidx.lifecycle.ViewModel
import com.fozimat.made.themovie.core.domain.model.Movie
import com.fozimat.made.themovie.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}