package com.arifandi.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifandi.capstone.core.domain.model.News
import com.arifandi.capstone.core.ui.NewsAdapter
import com.arifandi.capstone.detail.DetailNewsActivity
import com.arifandi.capstone.di.FavoriteModuleDependencies
import com.arifandi.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }
    private lateinit var binding:ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite News"

        val newsAdapter = NewsAdapter()
        newsAdapter.setOnItemClickCallback(object : NewsAdapter.OnItemClick{
            override fun onItemClicked(character: News) {
                val intentToDetail = Intent(this@FavoriteActivity, DetailNewsActivity::class.java)
                intentToDetail.putExtra(DetailNewsActivity.EXTRA_DATA, character)
                startActivity(intentToDetail)
            }
        })

        favoriteViewModel.favoriteTourism.observe(this) { dataNews ->
            newsAdapter.setData(dataNews)
            binding.viewEmpty.root.visibility =      if (dataNews.isNotEmpty()) View.GONE else View.VISIBLE

        }

        with(binding.rvTourism) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }
}