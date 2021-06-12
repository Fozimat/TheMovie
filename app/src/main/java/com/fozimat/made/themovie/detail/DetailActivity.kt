package com.fozimat.made.themovie.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fozimat.made.core.domain.model.Movie
import com.fozimat.made.core.utils.Constant.IMAGE_URL
import com.fozimat.made.themovie.R
import com.fozimat.made.themovie.databinding.ActivityDetailBinding
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        if (detailMovie != null) {
            showDetailMovie(detailMovie)
        }
    }

    private fun showDetailMovie(detailMovie: Movie) {
        with(binding) {
            supportActionBar?.title = detailMovie.title
            content.tvVoteAvg.text = detailMovie.vote_average.toString()
            content.tvVoteCount.text = detailMovie.vote_count.toString()
            content.tvPopular.text = detailMovie.popularity.toString()
            content.tvDetailOverview.text = detailMovie.overview
            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + detailMovie.poster_path)
                .into(ivDetailImage)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
                if (statusFavorite) {
                    Toasty.success(
                        this@DetailActivity,
                        "Data added successfully",
                        Toast.LENGTH_SHORT,
                        true
                    ).show()
                } else {
                    Toasty.success(
                        this@DetailActivity,
                        "Data deleted successfully",
                        Toast.LENGTH_SHORT,
                        true
                    ).show()
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}