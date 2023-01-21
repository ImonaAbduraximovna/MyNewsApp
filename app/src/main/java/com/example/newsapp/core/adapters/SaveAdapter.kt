package com.example.newsapp.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.core.models.NewTableModel
import com.example.newsapp.databinding.ItemListBinding
import java.text.SimpleDateFormat

class SaveAdapter : RecyclerView.Adapter<SaveAdapter.ViewHolder>() {

    private val data = ArrayList<NewTableModel>()
    private var onclick: OnSentMyClick? = null

    fun setMyClick(onclick: OnSentMyClick) {
        this.onclick = onclick
    }

    fun setData(d: List<NewTableModel>) {
        this.data.clear()
        this.data.addAll(d)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.data.clear()
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: NewTableModel) {

            val date = data.publishedAt
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputFormat = SimpleDateFormat("dd/MM")
            val parsedDate = inputFormat.parse(date)
            val formattedDate: String = outputFormat.format(parsedDate)

            binding.title.text = data.title
            binding.creatAt.text = formattedDate
            binding.image.load(data.urlToImage)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
        holder.itemView.setOnClickListener {
            onclick?.onMyClick(data[position])
        }
    }

    override fun getItemCount(): Int = data.size


    interface OnSentMyClick {
        fun onMyClick(data: NewTableModel)
    }

}