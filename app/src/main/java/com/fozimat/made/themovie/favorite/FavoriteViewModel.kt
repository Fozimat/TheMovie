package com.fozimat.made.themovie.favorite

import androidx.lifecycle.ViewModel
import com.fozimat.made.themovie.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovie = movieUseCase.getFavoriteMovie()
}