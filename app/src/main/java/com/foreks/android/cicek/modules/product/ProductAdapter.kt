package com.foreks.android.cicek.modules.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foreks.android.cicek.databinding.RowProductBinding

class ProductAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Product> = mutableListOf()
        set(value) {
            (items as MutableList).apply {
                clear()
                addAll(value)
            }
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(
            RowProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            holder.bindItem(items[position])
        }
    }

}

class ProductViewHolder(private val viewBinding: RowProductBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bindItem(product: Product) {
        viewBinding.apply {
            Glide.with(root.context)
                .load(product.image)
                .centerCrop()
                .into(imageView)
            textViewName.text = product.name
            textViewPrice.text = product.price.currentPrice.toString()
        }
    }
}
