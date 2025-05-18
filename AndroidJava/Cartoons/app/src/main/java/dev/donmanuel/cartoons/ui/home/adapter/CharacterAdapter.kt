package dev.donmanuel.cartoons.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.donmanuel.cartoons.R
import dev.donmanuel.cartoons.model.Futurama
import java.text.NumberFormat
import java.util.Locale

class CharacterAdapter :
    ListAdapter<Futurama, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvCategory: TextView = itemView.findViewById(R.id.tv_category)
        private val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        private val tvSlogan: TextView = itemView.findViewById(R.id.tv_slogan)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        private val tvStock: TextView = itemView.findViewById(R.id.tv_stock)

        fun bind(character: Futurama) {
            tvTitle.text = character.title
            tvCategory.text = character.category
            tvDescription.text = character.description
            tvSlogan.text = character.slogan

            // Format the price as currency
            val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
            tvPrice.text = numberFormat.format(character.price)

            tvStock.text = character.stock.toString()
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<Futurama>() {
        override fun areItemsTheSame(oldItem: Futurama, newItem: Futurama): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Futurama, newItem: Futurama): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.category == newItem.category &&
                    oldItem.description == newItem.description &&
                    oldItem.slogan == newItem.slogan &&
                    oldItem.price == newItem.price &&
                    oldItem.stock == newItem.stock
        }
    }
}
