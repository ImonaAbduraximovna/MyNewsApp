package com.example.newsapp.ui.main.saved

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.core.adapters.HomeAdapter
import com.example.newsapp.core.adapters.SaveAdapter
import com.example.newsapp.core.app.App.Companion.db
import com.example.newsapp.core.base.BaseFragment
import com.example.newsapp.core.models.Article
import com.example.newsapp.core.models.NewTableModel
import com.example.newsapp.databinding.PageSavedBinding


class SavedPage : BaseFragment(R.layout.page_saved) {

    private val binding by viewBinding(PageSavedBinding::bind)
    private val adapter = SaveAdapter()

    override fun onCreated(view: View, savedInstanceState: Bundle?) {

        setState()

    }

    private fun setState() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.setMyClick(object : SaveAdapter.OnSentMyClick {
            override fun onMyClick(data: NewTableModel) {
                val bundle = bundleOf(
                    "title" to data.title,
                    "image" to data.urlToImage,
                    "url" to data.url,
                    "date" to data.publishedAt,
                    "description" to data.description,
                )

                Navigation.findNavController(requireView()).navigate(R.id.itemScreen, bundle)

            }
        })

        binding.recyclerView.setHasFixedSize(true)

        val data = db?.getDao()?.getAllData()

        adapter.setData(data!!)

    }


}