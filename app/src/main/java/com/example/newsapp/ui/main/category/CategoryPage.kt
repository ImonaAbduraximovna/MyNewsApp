package com.example.newsapp.ui.main.category

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.core.adapters.CategoryAdapter
import com.example.newsapp.core.adapters.HomeAdapter
import com.example.newsapp.core.base.BaseFragment
import com.example.newsapp.core.models.Article
import com.example.newsapp.core.models.MyCategory
import com.example.newsapp.databinding.PageCategoryBinding
import com.example.newsapp.ui.main.MainViewModel


class CategoryPage : BaseFragment(R.layout.page_category) {

    private val binding by viewBinding(PageCategoryBinding::bind)
    private val categoryVM: MainViewModel by viewModels()

    private val adapter by lazy { CategoryAdapter() }

    override fun onCreated(view: View, savedInstanceState: Bundle?) {

        setState()
        adapter.clearData()
        categoryVM.getCategory("business")
        categoryVM.getCategory("entertainment")
        categoryVM.getCategory("general")
        categoryVM.getCategory("health")

        observ()


    }

    private fun setState() {

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.onClick = { data ->
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


    }



    private fun observ() {



        var a = 1

        categoryVM.topHeadlineLiveData.observe(viewLifecycleOwner) {

            a += 1

            adapter.addData(MyCategory(a, it))

        }


    }


}