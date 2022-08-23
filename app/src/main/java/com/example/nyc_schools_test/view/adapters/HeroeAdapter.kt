package com.example.nyc_schools_test.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nyc_schools_test.common.OnHeroeClicked
import com.example.nyc_schools_test.databinding.ItemLayoutBinding
import com.example.nyc_schools_test.model.remote.responses.HeroeDomain
import com.squareup.picasso.Picasso

class HeroeAdapter(
    private val onHeroeClicked: OnHeroeClicked,
    private val items: MutableList<HeroeDomain> = mutableListOf(),
) : RecyclerView.Adapter<HeroeAdapter.HeroeViewHolder>() {

    class HeroeViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateData(newItems: List<HeroeDomain>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun erraseData() {
        items.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroeViewHolder {
        return HeroeViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroeViewHolder, position: Int) {
        Picasso.get()
            .load(items[position].imageResponse.url)
            .into(holder.binding.moviePoster)
        holder.binding.heroeTxt.text = items[position].name

        holder.binding.movieView.setOnClickListener {
            onHeroeClicked.heroeClicked((items[position]))
        }
    }

    override fun getItemCount(): Int = items.size
}