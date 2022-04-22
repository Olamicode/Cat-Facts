package com.ola.consumewithinjection.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ola.consumewithinjection.data.remote.dto.CatFact
import com.ola.consumewithinjection.databinding.CatFactItemBinding

class CatFactAdapter(private var catFacts: List<CatFact>)
    : RecyclerView.Adapter<CatFactAdapter.CatFactViewHolder>() {

    class CatFactViewHolder(private val binding: CatFactItemBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bind(catFact: CatFact) {
                binding.apply {
                    factTitleTv.text = "Fact ${adapterPosition + 1}"
                    catFactTv.text = catFact.text
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactViewHolder {
        val binding = CatFactItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CatFactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatFactViewHolder, position: Int) {
        holder.bind(catFacts[position])
    }

    override fun getItemCount(): Int = catFacts.size

    fun submitList(catFacts: List<CatFact>) {
        this.catFacts = catFacts
        notifyDataSetChanged()
    }
}