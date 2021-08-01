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
import com.example.shoppingapp.databinding.ItemShopBinding
import com.example.shoppingapp.db.ShopRanking
import com.example.shoppingapp.ui.activities.DetailShopActivity
import com.example.shoppingapp.ui.fragments.ShopRankingFragment


class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {
    lateinit var context : Context

    inner class ShopViewHolder(private var binding : ItemShopBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : ShopRanking){
            binding.apply {
                rankingShopName.text = item.rankingShopName
                btnBookmark.isChecked = item.bookmarkButton

                itemView.setOnClickListener {
                    var intent = Intent(context, DetailShopActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
    val diffCallback = object : DiffUtil.ItemCallback<ShopRanking>(){
        override fun areItemsTheSame(oldItem: ShopRanking, newItem: ShopRanking): Boolean {
            return oldItem.shopId == newItem.shopId
        }

        override fun areContentsTheSame(oldItem: ShopRanking, newItem: ShopRanking): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<ShopRanking>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapter.ShopViewHolder {
        context=parent.context
        return ShopViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_shop,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ShopAdapter.ShopViewHolder, position: Int) {
        val shop = differ.currentList[position]
        holder.bind(shop)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}