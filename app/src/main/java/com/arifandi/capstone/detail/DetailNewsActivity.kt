package com.arifandi.capstone.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.arifandi.capstone.R
import com.arifandi.capstone.core.domain.model.News
import com.arifandi.capstone.databinding.ActivityDetailTourismBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNewsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailTourismBinding

    private val detailNewsViewModel: DetailNewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detailTourism = intent.getParcelableExtra<News>(EXTRA_DATA)
        if (detailTourism != null) {
            detailNewsViewModel.getNewsDetail(detailTourism.newsId.toString())
                .observe(this) { news ->
                    showDetailTourism(news)
                }
        }
    }

    private fun showDetailTourism(detailTourism: News?) {
        detailTourism?.let {
            supportActionBar?.title = detailTourism.title
            if (detailTourism.content?.isNotEmpty() == true){
                binding.content.tvDetailDescription.text = detailTourism.content
            }else{
                binding.content.tvDetailDescription.text = getString(R.string.no_data)
            }

            Glide.with(this@DetailNewsActivity)
                .load(detailTourism.urlToImage)
                .into(binding.ivDetailImage)

            var statusFavorite = detailTourism.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailNewsViewModel.setFavoriteTourism(detailTourism, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}