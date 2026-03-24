package com.example.memeapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import com.example.memeapp.ui.MemeViewModel
import com.example.memeapp.ui.MemeUiState

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MemeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.btnMeme)

        button.setOnClickListener {
            viewModel.getMeme()
            updateUI(imageView)
        }

        updateUI(imageView)
    }

    private fun updateUI(imageView: ImageView) {
        when (val state = viewModel.memeUiState) {

            is MemeUiState.Success -> {
                Picasso.get()
                    .load(state.url)
                    .into(imageView)
            }

            is MemeUiState.Loading -> {
            }

            is MemeUiState.Error -> {
            }
        }
    }
}