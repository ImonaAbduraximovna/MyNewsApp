package com.example.newsapp.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.core.models.Article
import com.example.newsapp.databinding.ItemCategoryBinding

class CategoryChildAdapter : RecyclerView.Adapter<CategoryChildAdapter.ViewHolder>() {

    private val data = ArrayList<Article>()
    var onClick: ((Article) -> Unit) ? = null

    fun setData(data: ArrayList<Article>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    fun clearData() {
        this.data.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: Article) {

            binding.imageView2.load(data.urlToImage)
            binding.title.text = data.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(data[position])
        holder.itemView.setOnClickListener {
            onClick?.invoke(data[position])
        }

    }

    override fun getItemCount(): Int = data.size

}