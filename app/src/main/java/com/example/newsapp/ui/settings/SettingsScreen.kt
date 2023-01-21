package com.example.newsapp.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.core.adapters.SettingsAdapter
import com.example.newsapp.core.base.BaseFragment
import com.example.newsapp.core.cache.LocalStorage
import com.example.newsapp.core.models.CountryModels
import com.example.newsapp.core.utils.getCountryData
import com.example.newsapp.databinding.ScreenSettingsBinding
import com.yariksoffice.lingver.Lingver

class SettingsScreen : BaseFragment(R.layout.screen_settings) {

    private val binding by viewBinding(ScreenSettingsBinding::bind)
    private val adapter by lazy { SettingsAdapter() }


    override fun onCreated(view: View, savedInstanceState: Bundle?) {


        binding.uzb.setOnClickListener {
            Lingver.getInstance().setLocale(requireContext(), "uz")
            Toast.makeText(context, "Uzbek", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        binding.eng.setOnClickListener {
            Lingver.getInstance().setLocale(requireContext(), "en")
            Toast.makeText(context, "Englsh", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

          binding.button.setOnClickListener {
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
          }

          binding.button2.setOnClickListener {
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
          }

          binding.button3.setOnClickListener {
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
          }


        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        setState()


    }

    private fun setState() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.setData(getCountryData())

        adapter.setMyClick(object : SettingsAdapter.OnSentMyClick {
            override fun onMyClick(data: CountryModels) {
                val cache = LocalStorage()
                cache.country = data.country
                Toast.makeText(context, data.country, Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        })
    }

}