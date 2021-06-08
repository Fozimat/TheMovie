package com.fozimat.made.themovie.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fozimat.made.themovie.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val movie = movieUseCase.getAllMovies().asLiveData()
}