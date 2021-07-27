package com.example.shoppingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ItemStuffBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.ui.activities.DetailStuffActivity

class StuffAdapter: RecyclerView.Adapter<StuffAdapter.StuffViewHolder>() {

    inner class StuffViewHolder(private val binding: ItemStuffBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Stuff){
            binding.apply {

                itemView.setOnClickListener {
                    var intent = Intent(context,DetailStuffActivity::class.java).apply {
                        //putExtra()
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
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
    lateinit var context : Context

    val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Stuff>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StuffViewHolder {
        context=parent.context
        return StuffViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_stuff,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: StuffViewHolder, position: Int) {
        val stuff = differ.currentList[position]
        holder.bind(stuff)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}