package com.example.shoppingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ItemShopBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.ui.activities.DetailShopActivity

class ShopAdapter: RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {
    lateinit var context : Context

    inner class ShopViewHolder(private var binding : ItemShopBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : Stuff){


            itemView.setOnClickListener {
                var intent = Intent(context, DetailShopActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(intent)
            }
        }
    }
    val diffCallback = object : DiffUtil.ItemCallback<Stuff>(){
        override fun areItemsTheSame(oldItem: Stuff, newItem: Stuff): Boolean {
            return oldItem.stuffId == newItem.stuffId
        }

        override fun areContentsTheSame(oldItem: Stuff, newItem: Stuff): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Stuff>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapter.ShopViewHolder {
        context=parent.context
        return ShopViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_stuff,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ShopAdapter.ShopViewHolder, position: Int) {
        val stuff = differ.currentList[position]
        holder.bind(stuff)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}