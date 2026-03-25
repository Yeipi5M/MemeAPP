package com.example.memeapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memeapp.databinding.ItemMemeBinding
import com.example.memeapp.model.MemeRedditResponse
import com.squareup.picasso.Picasso

class MemeAdapter(
    private val memes: MutableList<MemeRedditResponse>
) : RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {

    class MemeViewHolder(val binding: ItemMemeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val binding = ItemMemeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val meme = memes[position]


        Picasso.get()
            .load(meme.url)
            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int = memes.size

    fun addMemes(newMemes: List<MemeRedditResponse>) {
        memes.addAll(newMemes)
        notifyDataSetChanged()
    }
}