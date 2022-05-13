package com.webecom.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.webecom.R
import com.webecom.activity.ProductActivity
import com.webecom.model.ProductModel
import kotlinx.android.synthetic.main.row_product.view.*

class CategoryProductAdapter(private var items: ArrayList<ProductModel>) :
    RecyclerView.Adapter<CategoryProductAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var lyMain: LinearLayout = itemView.findViewById(R.id.lyMain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_category_product, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {

        Glide
            .with(holder.itemView.context)
            .load(items[position].icon)
            .centerCrop()
            .into(holder.itemView.imgProduct);
        holder.itemView.tvTitle.text=items[position].title

        holder.itemView.tvDiscount.text = items[position].discount

        holder.lyMain.setOnClickListener {
            val intent=Intent(context,ProductActivity::class.java)
            context.startActivity(intent)
        }
    }

}
