package com.example.memeapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.memeapp.ui.MemeAdapter
import com.example.memeapp.ui.MemeState
import com.example.memeapp.ui.MemeViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MemeViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: MemeAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var stateImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MemeViewModel::class.java]

        viewPager = findViewById(R.id.viewPager)
        progressBar = findViewById(R.id.progressBar)
        stateImage = findViewById(R.id.stateImage)

        adapter = MemeAdapter(mutableListOf())
        viewPager.adapter = adapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL


        viewModel.getMemeList()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == adapter.itemCount - 1) {
                    viewModel.getMemeList()
                }
            }
        })


        viewModel.state.observe(this) { state ->

            when (state) {

                is MemeState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    stateImage.visibility = View.VISIBLE
                    viewPager.visibility = View.GONE

                    stateImage.setImageResource(R.drawable.loading)
                }

                is MemeState.Success -> {
                    progressBar.visibility = View.GONE
                    stateImage.visibility = View.GONE
                    viewPager.visibility = View.VISIBLE

                    adapter.addMemes(state.memes)
                }

                is MemeState.Error -> {
                    progressBar.visibility = View.GONE
                    stateImage.visibility = View.VISIBLE
                    viewPager.visibility = View.GONE

                    stateImage.setImageResource(R.drawable.error)
                }
            }
        }
    }
}