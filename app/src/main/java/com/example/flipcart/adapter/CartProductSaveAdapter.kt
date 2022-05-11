package com.example.flipcart.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flipcart.R
import com.example.flipcart.model.FilterChildModel
import com.example.flipcart.model.NavigationItemModel
import com.example.flipcart.model.ProductModel
import com.example.flipcart.model.ReviewsModel
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.row_cart_product.view.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.navigation_title
import kotlinx.android.synthetic.main.row_reviews.view.*

class CartProductSaveAdapter(private var items: ArrayList<ProductModel>) :
    RecyclerView.Adapter<CartProductSaveAdapter.NavigationItemViewHolder>(){

    private lateinit var context: Context
    var itemList = ArrayList<ProductModel>()
    init {
        this.itemList = items
    }
    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_cart_save_product, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
            return 2//itemList.count()
    }
    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        //holder.itemView.tvRating.text = itemList[position].review
        //holder.itemView.title.text = itemList[position].title

        holder.itemView.tvOldPrice.paintFlags = holder.itemView.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

}
