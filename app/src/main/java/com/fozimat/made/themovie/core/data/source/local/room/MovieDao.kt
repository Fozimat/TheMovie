package com.fozimat.made.themovie.core.data.source.local.room

import androidx.room.*
import com.fozimat.made.themovie.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM trending_movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM trending_movie where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}