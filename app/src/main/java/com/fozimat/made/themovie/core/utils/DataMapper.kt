package com.fozimat.made.themovie.core.utils

import com.fozimat.made.themovie.core.data.source.local.entity.MovieEntity
import com.fozimat.made.themovie.core.data.source.remote.response.MovieResponse
import com.fozimat.made.themovie.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.id,
                it.title,
                it.overview,
                it.voteAverage,
                it.voteCount,
                it.popularity,
                it.posterPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.title,
                it.overview,
                it.vote_average,
                it.vote_count,
                it.popularity,
                it.poster_path,
                isFavorite = false
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        input.id,
        input.title,
        input.overview,
        input.vote_average,
        input.vote_count,
        input.popularity,
        input.poster_path,
        input.isFavorite
    )
}