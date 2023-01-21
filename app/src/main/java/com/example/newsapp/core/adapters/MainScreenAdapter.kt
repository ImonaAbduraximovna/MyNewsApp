package com.example.newsapp.core.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.ui.main.category.CategoryPage
import com.example.newsapp.ui.main.home.HomePage
import com.example.newsapp.ui.main.saved.SavedPage

class MainScreenAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomePage()
            1 -> CategoryPage()
            else -> SavedPage()
        }
    }

}