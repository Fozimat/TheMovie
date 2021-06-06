package com.fozimat.made.themovie.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fozimat.made.themovie.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM trending_movie")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM trending_movie where isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}