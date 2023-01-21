package com.example.newsapp.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.core.models.Article
import com.example.newsapp.core.models.MyCategory
import com.example.newsapp.databinding.ItemStateCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val data = ArrayList<MyCategory>()
    var onClick : ((Article) -> Unit)? = null

    fun addData(data: MyCategory) {
        this.data.add(data)
        notifyDataSetChanged()
    }


    fun clearData() {
        this.data.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemStateCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val adapter = CategoryChildAdapter()

        fun bindData(data: MyCategory) {

            when (data.id) {
                1 -> binding.category.text = "business"
                2 -> binding.category.text = "entertainment"
                3 -> binding.category.text = "general"
                4 -> binding.category.text = "health"
            }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager =
                LinearLayoutManager(binding.category.context, HORIZONTAL, false)

            adapter.setData(data.result as ArrayList<Article>)

            adapter.onClick={
                onClick?.invoke(it)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStateCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size


}