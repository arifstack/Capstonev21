package com.arifandi.capstone.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifandi.capstone.R
import com.arifandi.capstone.core.data.Resource
import com.arifandi.capstone.core.domain.model.News
import com.arifandi.capstone.core.ui.NewsAdapter
import com.arifandi.capstone.databinding.FragmentHomeBinding
import com.arifandi.capstone.detail.DetailNewsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val newsAdapter = NewsAdapter()
            newsAdapter.setOnItemClickCallback(object : NewsAdapter.onItemClick{
                override fun onItemClicked(character: News) {
                    val intentToDetail = Intent(activity, DetailNewsActivity::class.java)
                    intentToDetail.putExtra(DetailNewsActivity.EXTRA_DATA, character)
                    startActivity(intentToDetail)
                }
            })

            homeViewModel.news.observe(viewLifecycleOwner) { news ->
                if (news != null) {
                    when (news) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            newsAdapter.setData(news.articles)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                news.status ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvNews) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.news.asFlow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}