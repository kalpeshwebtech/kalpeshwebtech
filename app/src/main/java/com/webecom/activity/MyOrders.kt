package com.webecom.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.webecom.R
import com.webecom.adapter.BottomSheetAdapter
import com.webecom.adapter.MyOrderAdapter
import com.webecom.utils.RecyclerItemClickListener
import com.webecom.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.toolbar.*

class MyOrders : AppCompatActivity(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: SearchView
    lateinit var bottomSheet : BottomSheetDialog

    var lastSelected=0
    private var items2= Utils.getProduct()
    val array =Utils.getSortBy()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)
        init()
    }

    private fun init() {
        imgBack.visibility = View.VISIBLE
        imgLike.visibility = View.VISIBLE
        flBadge.visibility = View.VISIBLE
        headerText.text = "My Orders"

        searchBar =findViewById(R.id.searchBar)
        searchBar.setIconifiedByDefault(false)
        searchBar.queryHint = resources.getString(R.string.search_order)

        recyclerView = findViewById(R.id.recyclerView)
        val orderAdapter= MyOrderAdapter(items2);
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.addItemDecoration(SpacesItemDecoration(5))
        recyclerView.adapter=orderAdapter
        recyclerView.isNestedScrollingEnabled = false;

        imgBack.setOnClickListener(this)
        imgLike.setOnClickListener(this)
        flBadge.setOnClickListener(this)
        lyFilter.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBack -> {
                onBackPressed()
            }
            R.id.imgLike -> {}
            R.id.flBadge -> {
                val intent= Intent(this, MyCartActivity::class.java)
                startActivity(intent)
            }
//            R.id.lySort->{
//                sortDialog()
//            }
            R.id.lyFilter->{
                sortDialog()
            }
            else -> {}
        }
    }

    private fun sortDialog() {
        bottomSheet = BottomSheetDialog(this,R.style.BottomSheetStyle)
        val mylayout = findViewById<LinearLayout>(R.id.sheet)
        val viewDailog = LayoutInflater.from(this).inflate(R.layout.order_filter_dailog,mylayout, false)
        bottomSheet.setContentView(viewDailog)
        bottomSheet.show()

        val recyclerView = bottomSheet.findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.adapter =  BottomSheetAdapter(this, array ,lastSelected);
        recyclerView?.addOnItemTouchListener(RecyclerItemClickListener(this, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                lastSelected=position;
                bottomSheet.dismiss()
            }
            override fun onItemLongClick(view: View?, position: Int) {

            }
        }))
    }
    override fun onResume() {
        super.onResume()
        updateCartBadge()
    }
    private fun updateCartBadge() {
        val count = Utils.getCart().size
        if (count > 0) {
            tvBadge.visibility = View.VISIBLE
            tvBadge.text = "$count"
        } else {
            tvBadge.visibility = View.GONE
        }
    }
}