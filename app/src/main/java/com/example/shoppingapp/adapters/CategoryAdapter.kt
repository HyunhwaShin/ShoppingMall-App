package com.example.shoppingapp.adapters

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ItemStuffBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.ui.activities.DetailStuffActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val binding: ItemStuffBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Stuff){
            binding.apply {
                stuffName.text = item.product_name
                shopName.text = item.shop_name
                item.product_price.toString().also { price.text = it }
                btnCheckbox.isChecked = item.checkBox
                btnFavorite.isChecked = item.isLike

                var launch = CoroutineScope(Dispatchers.IO).launch {
                    val inputStream = URL(item.product_img).openStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    withContext(Dispatchers.Main) {
                        Glide.with(binding.root)
                            .load(bitmap)
                            .into(stuffImage)
                    }
                }


                itemView.setOnClickListener {
                    var intent = Intent(context, DetailStuffActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
    val diffCallback = object : DiffUtil.ItemCallback<Stuff>(){
        override fun areItemsTheSame(oldItem: Stuff, newItem: Stuff): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Stuff, newItem: Stuff): Boolean {
            return oldItem == newItem
        }
    }
    lateinit var context : Context

    val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Stuff>) = differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context=parent.context
        return CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_stuff,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val stuff = differ.currentList[position]
        holder.bind(stuff)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}