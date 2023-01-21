package com.example.newsapp.ui.searchScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.core.FragmentDataChange
import com.example.newsapp.core.adapters.HomeAdapter
import com.example.newsapp.databinding.ScreenSearchBinding
import com.example.newsapp.ui.main.MainScreenDirections
import com.example.newsapp.ui.main.MainViewModel


class SearchFragment : Fragment(), FragmentDataChange {

    private val mainMV: MainViewModel by viewModels()


    private val binding: ScreenSearchBinding by lazy {
        ScreenSearchBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        HomeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.searchListView.adapter = adapter
        binding.searchListView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchView.clearFocus()
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text.toString().trim().isNotEmpty()) {
                    mainMV.searchNews(text.toString().trim())
                } else {
                    adapter.clearData()
                }
            }

            override fun afterTextChanged(text: Editable?) {

            }
        })

        mainMV.searchLiveData.observe(this@SearchFragment) {
            adapter.setData(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
        findNavController().navigate(MainScreenDirections.actionMainScreenToSearchFragment())
    }

    override fun changedData() {}
}