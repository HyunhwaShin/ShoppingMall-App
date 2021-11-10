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
import com.example.shoppingapp.db.StoreLikeDto
import com.example.shoppingapp.repositories.UserRepository
import com.example.shoppingapp.ui.activities.DetailShopActivity
import com.example.shoppingapp.viewmodels.ShopRankingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class ShopRankingAdapter(private val shopRankingViewModel: ShopRankingViewModel) : RecyclerView.Adapter<ShopRankingAdapter.ShopViewHolder>() {
    lateinit var context : Context

    inner class ShopViewHolder(private var binding : ItemShopBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : ShopRanking){
            binding.apply {
                rankingShopName.text = item.shop_name
                btnBookmark.isChecked = item.isBookmark
                btnBookmark.setOnCheckedChangeListener { btn, isCheck ->
                    shopRankingViewModel.postStoreLike(item.uid)
                }

                itemView.setOnClickListener {
                    var intent = Intent(context, DetailShopActivity::class.java).apply {
                        putExtra("shop", item)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
    val diffCallback = object : DiffUtil.ItemCallback<ShopRanking>(){
        override fun areItemsTheSame(oldItem: ShopRanking, newItem: ShopRanking): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: ShopRanking, newItem: ShopRanking): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<ShopRanking>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopRankingAdapter.ShopViewHolder {
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
    override fun onBindViewHolder(holder: ShopRankingAdapter.ShopViewHolder, position: Int) {
        val shop = differ.currentList[position]
        holder.bind(shop)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}