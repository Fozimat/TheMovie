package com.fozimat.made.themovie.core.domain.usecase

import com.fozimat.made.themovie.core.data.Resource
import com.fozimat.made.themovie.core.domain.model.Movie
import com.fozimat.made.themovie.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovies()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return movieRepository.setFavoriteMovie(movie, state)
    }
}