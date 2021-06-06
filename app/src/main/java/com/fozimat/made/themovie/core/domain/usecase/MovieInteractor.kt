package com.fozimat.made.themovie.core.domain.usecase

import androidx.lifecycle.LiveData
import com.fozimat.made.themovie.core.data.Resource
import com.fozimat.made.themovie.core.domain.model.Movie
import com.fozimat.made.themovie.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovies(): LiveData<Resource<List<Movie>>> {
        return movieRepository.getAllMovies()
    }

    override fun getFavoriteMovie(): LiveData<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return movieRepository.setFavoriteMovie(movie, state)
    }
}