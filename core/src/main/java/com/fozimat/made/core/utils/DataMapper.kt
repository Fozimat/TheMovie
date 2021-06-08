package com.fozimat.made.core.utils

import com.fozimat.made.core.data.source.local.entity.MovieEntity
import com.fozimat.made.core.data.source.remote.response.MovieResponse
import com.fozimat.made.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                popularity = it.popularity,
                poster_path = it.poster_path,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                popularity = it.popularity,
                poster_path = it.poster_path,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            vote_average = input.vote_average,
            vote_count = input.vote_count,
            popularity = input.popularity,
            poster_path = input.poster_path,
            isFavorite = input.isFavorite
        )
}