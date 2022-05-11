package com.example.flipcart.activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.flipcart.R
import com.example.flipcart.adapter.*
import com.example.flipcart.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.toolbar.*

class MyCartActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private var items2= Utils.getProduct()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart2)
        init()
    }

    private fun init() {
        imgBack.visibility = View.VISIBLE
        headerText.text = "My Cart"
        recyclerView = findViewById(R.id.recyclerView)


        val productAdapter= CartProductAdapter(items2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter=productAdapter
        recyclerView.isNestedScrollingEnabled = false;


        val saveAdapter= CartProductSaveAdapter(items2)
        recyclerViewSave.layoutManager = LinearLayoutManager(this)
        recyclerViewSave.setHasFixedSize(true)
        recyclerViewSave.adapter=saveAdapter
        recyclerViewSave.isNestedScrollingEnabled = false;

        imgBack.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBack -> {
                onBackPressed()
            }
            else -> {
            }
        }
    }

}