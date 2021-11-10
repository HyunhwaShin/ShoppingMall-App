package com.example.shoppingapp.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ItemDeliveryBinding
import com.example.shoppingapp.db.Delivery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class DeliveryAdapter: RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {
    lateinit var context : Context

    inner class DeliveryViewHolder(var binding : ItemDeliveryBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Delivery){
            binding.apply {
                deliveryDate.text = item.orderDate
                 shopName.text = item.shopName
                 stuffName.text = item.name
                deliveryStatus.text = item.orderInfo
            }
            var launch = CoroutineScope(Dispatchers.IO).launch {
                val inputStream = URL(item.img).openStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                withContext(Dispatchers.Main) {
                    Glide.with(binding.root)
                        .load(bitmap)
                        .into(binding.stuffImage)
                }
            }
        }
    }

    val differCallback = object : DiffUtil.ItemCallback<Delivery>(){
        override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
            return oldItem.orderNumber == newItem.orderNumber
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