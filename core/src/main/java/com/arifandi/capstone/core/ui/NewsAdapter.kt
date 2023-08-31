package com.arifandi.capstone.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arifandi.capstone.core.R
import com.arifandi.capstone.core.databinding.ItemListNewsBinding
import com.arifandi.capstone.core.domain.model.News
import com.bumptech.glide.Glide
import java.util.ArrayList

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.CharacterViewHolder>() {

    private var onItemClickCallBack: OnItemClick? = null

    private var characterListData = ArrayList<News>()
    fun setData(newCharacterListData: List<News>?) {
        if (newCharacterListData == null) return
        val diffCallback = NewsDiffCallback(characterListData, newCharacterListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        characterListData.clear()
        characterListData.addAll(newCharacterListData)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class CharacterViewHolder(var binding: ItemListNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val data = characterListData[position]
        holder.apply {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .placeholder(R.mipmap.ic_empty_news)
                    .error(R.mipmap.ic_empty_news)
                    .into(imgNews)
                lblTitle.text = data.title
                itemView.setOnClickListener {
                    onItemClickCallBack?.onItemClicked(data)
                }
            }
        }
    }

    override fun getItemCount(): Int = characterListData.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClick) {
        this.onItemClickCallBack = onItemClickCallback
    }

    interface OnItemClick {
        fun onItemClicked(character: News)
    }
}
