package com.fozimat.made.themovie.core.data.source.local

import androidx.lifecycle.LiveData
import com.fozimat.made.themovie.core.data.source.local.entity.MovieEntity
import com.fozimat.made.themovie.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao){

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}