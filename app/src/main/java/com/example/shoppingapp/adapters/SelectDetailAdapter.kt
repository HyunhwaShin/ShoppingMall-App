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
import com.example.shoppingapp.databinding.ItemBasketBinding
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.viewmodels.SelectedDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class SelectDetailAdapter(private val selectedDetailViewModel: SelectedDetailViewModel): RecyclerView.Adapter<SelectDetailAdapter.SelectDetailViewHolder>() {

    inner class SelectDetailViewHolder(private val binding : ItemBasketBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item : BasketStuff) {

            binding.apply {
                stuffName.text = item.product_name
                stuffPrice.text = item.product_price.toString()

                var launch = CoroutineScope(Dispatchers.IO).launch {
                    val inputStream = URL(item.product_img).openStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    withContext(Dispatchers.Main) {
                        Glide.with(binding.root)
                            .load(bitmap)
                            .into(stuffImage)
                    }
                }

                btnCheck.setOnCheckedChangeListener { btn, isCheck ->
                    selectedDetailViewModel.toggleGoBasketItemList(isCheck,item)
                }

            }
        }
    }
    val diffCallback = object : DiffUtil.ItemCallback<BasketStuff>(){
        override fun areItemsTheSame(oldItem: BasketStuff, newItem: BasketStuff): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: BasketStuff, newItem: BasketStuff): Boolean {
            return oldItem == newItem
        }
    }
    lateinit var context : Context

    val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<BasketStuff>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectDetailViewHolder {
        context = parent.context
        return SelectDetailViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_basket,
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: SelectDetailViewHolder, position: Int) {
        val basketStuff = differ.currentList[position]
        holder.bind(basketStuff)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}