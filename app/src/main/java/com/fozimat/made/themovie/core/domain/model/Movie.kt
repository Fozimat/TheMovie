package com.fozimat.made.themovie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val vote_count: Int,
    val popularity: Double,
    val poster_path: String,
    val isFavorite: Boolean
) : Parcelable