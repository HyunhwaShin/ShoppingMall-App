package com.example.shoppingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ItemDeliveryBinding
import com.example.shoppingapp.db.Delivery

class DeliveryAdapter: RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {
    lateinit var context : Context

    inner class DeliveryViewHolder(var binding : ItemDeliveryBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Delivery){
            binding.apply {
                deliveryDate.text = item.deliveryDate
                // shopName.text = item.deliveryShop
                // stuffName.text = item.deliveryStuff
                deliveryStatus.text = item.deliveryStatus
            }
        }

    }

    val differCallback = object : DiffUtil.ItemCallback<Delivery>(){
        override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            return oldItem.deliveryId == newItem.deliveryId
        }

        override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)

    fun submitList(list : List<Delivery>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        context=parent.context

        return DeliveryViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.item_delivery,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: DeliveryAdapter.DeliveryViewHolder, position: Int) {
        val delivery = differ.currentList[position]
        holder.bind(delivery)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}