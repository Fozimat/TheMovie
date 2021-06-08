package com.fozimat.made.themovie.di

import com.fozimat.made.core.domain.usecase.MovieInteractor
import com.fozimat.made.core.domain.usecase.MovieUseCase
import com.fozimat.made.themovie.detail.DetailViewModel
import com.fozimat.made.themovie.favorite.FavoriteViewModel
import com.fozimat.made.themovie.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}