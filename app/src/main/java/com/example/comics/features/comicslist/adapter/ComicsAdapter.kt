package com.example.comics.features.comicslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comics.databinding.ItemListBinding
import com.example.comics.features.comicslist.model.ComicsViewObject

class ComicsAdapter(private val list: List<ComicsViewObject>) :
    RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    lateinit var onClickItem: (ComicsViewObject) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bindComics(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ComicsViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindComics(viewObject: ComicsViewObject) {
            with(viewObject) {
                binding.run {
                    Glide.with(this.root)
                        .load(this@with.image)
                        .centerCrop()
                        .into(this.actionImage)

                    actionTitle.text = this@with.title
                    actionSubTitle.text = this@with.subtitle

                    this.root.setOnClickListener {
                        if (::onClickItem.isInitialized) {
                            onClickItem.invoke(this@with)
                        }
                    }
                }
            }
        }
    }
}