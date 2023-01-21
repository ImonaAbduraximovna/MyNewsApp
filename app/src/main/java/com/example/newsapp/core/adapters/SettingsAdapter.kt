package com.example.newsapp.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.core.models.Article
import com.example.newsapp.core.models.CountryModels
import com.example.newsapp.databinding.ItemCountryBinding
import com.example.newsapp.databinding.ItemListBinding
import java.text.SimpleDateFormat

class SettingsAdapter : RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {

    private val data = ArrayList<CountryModels>()
    private var onclick: OnSentMyClick? = null

    fun setMyClick(onclick: OnSentMyClick) {
        this.onclick = onclick
    }

    fun setData(d: List<CountryModels>) {
        this.data.clear()
        this.data.addAll(d)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.data.clear()
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: CountryModels) {

            binding.title.text = data.title

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(data[position])
        holder.itemView.setOnClickListener {
            onclick?.onMyClick(data[position])
        }
    }

    override fun getItemCount(): Int = data.size


    interface OnSentMyClick {
        fun onMyClick(data: CountryModels)
    }

}