package com.example.flipcart.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flipcart.R
import com.example.flipcart.adapter.BottomSheetAdapter
import com.example.flipcart.adapter.CategoryProductAdapter
import com.example.flipcart.adapter.ProductAdapter
import com.example.flipcart.model.ProductModel
import com.example.flipcart.utils.RecyclerItemClickListener
import com.example.flipcart.utils.SpacesItemDecoration
import com.example.flipcart.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    lateinit var bottomSheet : BottomSheetDialog

    var lastSelected=0
    private var items2= Utils.getProduct()
    val array =Utils.getSortBy()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        init()
    }

    private fun init() {
        imgBack.visibility = View.VISIBLE
        imgLike.visibility = View.VISIBLE
        imgCart.visibility = View.VISIBLE
        headerText.text = "Product List"

        recyclerView = findViewById(R.id.recyclerView)
        var productAdapter= ProductAdapter(items2);
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        //recyclerView.addItemDecoration(SpacesItemDecoration(5))
        recyclerView.adapter=productAdapter
        recyclerView.isNestedScrollingEnabled = false;

        imgBack.setOnClickListener(this)
        imgLike.setOnClickListener(this)
        imgCart.setOnClickListener(this)
        lySort.setOnClickListener(this)
        lyFilter.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBack -> {
                onBackPressed()
            }
            R.id.imgLike -> {}
            R.id.imgCart -> {
                val intent= Intent(this, MyCartActivity::class.java)
                startActivity(intent)
            }
            R.id.lySort->{
                sortDialog()
            }
            R.id.lyFilter->{
                val intent= Intent(this, FilterActivity::class.java)
               startActivity(intent)
            }
            else -> {}
        }
    }

    private fun sortDialog() {
        bottomSheet = BottomSheetDialog(this,R.style.BottomSheetStyle)
        val mylayout = findViewById<LinearLayout>(R.id.sheet)
        val viewDailog = LayoutInflater.from(this).inflate(R.layout.bottomsheet_dailog,mylayout, false)
        bottomSheet.setContentView(viewDailog)
        bottomSheet.show()


        var recyclerView = bottomSheet.findViewById<RecyclerView>(R.id.recyclerView)
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
}