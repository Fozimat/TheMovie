package com.fozimat.made.themovie.core.di

import android.content.Context
import com.fozimat.made.themovie.core.data.MovieRepository
import com.fozimat.made.themovie.core.data.source.local.LocalDataSource
import com.fozimat.made.themovie.core.data.source.local.room.MovieDatabase
import com.fozimat.made.themovie.core.data.source.remote.RemoteDataSource
import com.fozimat.made.themovie.core.data.source.remote.network.ApiConfig
import com.fozimat.made.themovie.core.domain.repository.IMovieRepository
import com.fozimat.made.themovie.core.domain.usecase.MovieInteractor
import com.fozimat.made.themovie.core.domain.usecase.MovieUseCase
import com.fozimat.made.themovie.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}