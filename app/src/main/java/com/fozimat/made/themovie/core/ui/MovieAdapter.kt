package com.fozimat.made.themovie.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fozimat.made.themovie.core.domain.model.Movie
import com.fozimat.made.themovie.core.utils.Constant.IMAGE_URL
import com.fozimat.made.themovie.databinding.ItemListMoviesBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MoviesListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val itemsBinding =
            ItemListMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesListViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class MoviesListViewHolder(private val binding: ItemListMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemAverage.text = movie.vote_average.toString()
                Glide.with(itemView.context)
                    .load(IMAGE_URL + movie.poster_path)
                    .into(ivItemImage)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[layoutPosition])
            }
        }
    }
}