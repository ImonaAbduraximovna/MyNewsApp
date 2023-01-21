package com.example.newsapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.core.adapters.MainScreenAdapter
import com.example.newsapp.core.base.BaseFragment
import com.example.newsapp.core.utils.SetItemStatusBarColor
import com.example.newsapp.databinding.ScreenMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainScreen : BaseFragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)


    override fun onCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MainScreenAdapter(childFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter
        binding.viewPager2.isUserInputEnabled = false

        SetItemStatusBarColor(Color.WHITE, true)

        binding.bottomNavigationView.setOnItemSelectedListener { result ->

            when (result.itemId) {
                R.id.home_page -> binding.viewPager2.setCurrentItem(0, false)
                R.id.category_page -> binding.viewPager2.setCurrentItem(1, false)
                R.id.saved_page -> binding.viewPager2.setCurrentItem(2, false)
            }

            return@setOnItemSelectedListener true
        }


        binding.searchBtn.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToSearchFragment())
        }

        binding.settings.setOnClickListener {

            findNavController().navigate(MainScreenDirections.actionMainScreenToSettingsScreen())

        }


    }


}