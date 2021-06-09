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

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie.let {
            supportActionBar?.title = detailMovie?.title
            binding.content.tvVoteAvg.text = detailMovie?.vote_average.toString()
            binding.content.tvVoteCount.text = detailMovie?.vote_count.toString()
            binding.content.tvPopular.text = detailMovie?.popularity.toString()
            binding.content.tvDetailOverview.text = detailMovie?.overview
            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + detailMovie?.poster_path)
                .into(binding.ivDetailImage)

            var statusFavorite = detailMovie?.isFavorite
            if (statusFavorite != null) {
                setStatusFavorite(statusFavorite)
            }
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite!!
                if (detailMovie != null) {
                    detailViewModel.setFavoriteMovie(detailMovie, statusFavorite!!)
                }
                setStatusFavorite(statusFavorite!!)
                if (statusFavorite as Boolean) {
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
}