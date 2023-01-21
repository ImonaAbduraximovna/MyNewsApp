package com.example.newsapp.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.core.adapters.HomeAdapter
import com.example.newsapp.core.base.BaseFragment
import com.example.newsapp.core.cache.LocalStorage
import com.example.newsapp.core.models.Article
import com.example.newsapp.databinding.PageHomeBinding
import com.example.newsapp.ui.main.MainViewModel

class HomePage : BaseFragment(R.layout.page_home) {

    private val binding by viewBinding(PageHomeBinding::bind)
    private val adapter by lazy { HomeAdapter() }
    private val homeVM: MainViewModel by viewModels()
    private val local = LocalStorage()


    override fun onCreated(view: View, savedInstanceState: Bundle?) {


        setState()
        observe()

    }

    private fun observe() {

        homeVM.loadTopHeadline(local.country)

        homeVM.topHeadlineLiveData.observe(viewLifecycleOwner) { response ->
            adapter.setData(response)
        }

    }

    private fun setState() {

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.setMyClick(object : HomeAdapter.OnSentMyClick {
            override fun onMyClick(data: Article) {
                val bundle = bundleOf(
                    "title" to data.title,
                    "image" to data.urlToImage,
                    "author" to data.author,
                    "url" to data.url,
                    "date" to data.publishedAt,
                    "description" to data.description,
                    "content" to data.content
                )

                Navigation.findNavController(requireView()).navigate(R.id.itemScreen, bundle)

            }
        })

    }

}