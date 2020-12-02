package com.example.worktest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.worktest.databinding.ItemImageBinding
import com.example.worktest.model.Image
import com.example.worktest.util.loadFromUrl

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var images = listOf<Image>()

    fun setData(images: List<Image>) {
        this.images = images
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) {
            binding.image.loadFromUrl(image.url)
            binding.author.text = image.author
        }
    }
}