package com.example.shoppingapp.adapters
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ItemStuffBinding
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.ui.activities.DetailStuffActivity
import com.example.shoppingapp.viewmodels.LikeViewModel

class LikeAdapter(private val viewModel: LikeViewModel): RecyclerView.Adapter<LikeAdapter.LikeViewHolder>() {

    private var check = 0

    inner class LikeViewHolder(private val binding: ItemStuffBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Stuff){
            binding.apply {
                stuffName.text = item.stuffName
                shopName.text = item.shopName
                btnCheckbox.isChecked = item.checkBox

                btnCheckbox.setOnCheckedChangeListener { btn, isCheck ->
                    viewModel.toggleDeleteItemList(isCheck,item)
                }

                itemView.setOnClickListener {
                    if(check ==1 ){
                        // LikeFragment 의 편집모드일 때는 상세화면으로 이동 x
                    }else{
                        var intent = Intent(context,DetailStuffActivity::class.java).apply {
                            //putExtra()
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        context.startActivity(intent)
                    }
                }
            }
            //checkBox 숨김 or 보임 처리
            if(check ==1 ){
                binding.btnCheckbox.visibility = View.VISIBLE
            }else{
                binding.btnCheckbox.visibility = View.GONE
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

    fun updateCheckbox(n:Int){ check = n}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        context=parent.context
        return LikeViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_stuff,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        val stuff = differ.currentList[position]
        holder.bind(stuff)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}