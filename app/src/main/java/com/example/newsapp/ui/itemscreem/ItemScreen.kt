package com.example.newsapp.ui.itemscreem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.newsapp.R
import com.example.newsapp.core.app.App.Companion.db
import com.example.newsapp.core.base.BaseFragment
import com.example.newsapp.core.models.NewTableModel
import com.example.newsapp.databinding.ScreenItemBinding


class ItemScreen : BaseFragment(R.layout.screen_item) {

    private val binding by viewBinding(ScreenItemBinding::bind)

    private var isSaved = false
    private var shareDataa = ""

    override fun onCreated(view: View, savedInstanceState: Bundle?) {
        setData()
        binding.back.setOnClickListener {
            findNavController().popBackStack()

        }

        binding.share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,shareDataa)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }

    }



    private fun setData() {
        val title = requireArguments().getString("title")!!
        val image = requireArguments().getString("image")!!
        val url = requireArguments().getString("url")!!
        val date = requireArguments().getString("date")!!
        val description = requireArguments().getString("description")!!

        shareDataa = url

        val images = db?.getDao()?.getImages()!!

        for (i in images) {
            if (i == image) {
                binding.save.setImageResource(R.drawable.ic_round_bookmark_24)
                isSaved = true
                break
            }
        }

        binding.title.text = title
        binding.disc.text = description
        binding.imageView.load(image)
        binding.dateData.text = date


        binding.officialsite.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)

        }

        binding.save.setOnClickListener {

            val data = NewTableModel(title, description, url, image, date)

            if (isSaved) {
                binding.save.setImageResource(R.drawable.icon_bookmark)
                db?.getDao()?.deleteData(image)
                isSaved = false
            } else {
                binding.save.setImageResource(R.drawable.ic_round_bookmark_24)
                db?.getDao()?.insertData(data)
                isSaved = true
            }

        }


    }

}