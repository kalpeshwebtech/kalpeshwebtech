package com.webecom.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.webecom.R
import com.webecom.model.ProductModel
import kotlinx.android.synthetic.main.row_cart_product.view.*
import android.widget.AdapterView
import androidx.core.content.ContextCompat


class MyOrderAdapter(private var items: ArrayList<ProductModel>) :
    RecyclerView.Adapter<MyOrderAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context
    var itemList = ArrayList<ProductModel>()
    init {
        this.itemList = items
    }

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem =
            LayoutInflater.from(parent.context).inflate(R.layout.row_my_order, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return 2//itemList.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {

        val data=items[position]

    }

}
