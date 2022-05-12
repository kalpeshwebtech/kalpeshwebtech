package com.webecom.activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.webecom.R
import com.webecom.adapter.CustomAdapter
import com.webecom.adapter.ReviewsAdapter
import com.webecom.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductDetailsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var bottomSheet: BottomSheetDialog
    private lateinit var viewPager: ViewPager
    private lateinit var recyclerView: RecyclerView
    private lateinit var pageIndicatorView: PageIndicatorView
    private lateinit var customAdapter: CustomAdapter

    private var items2 = Utils.getReviews()
    var imageId =
        arrayOf<Int>(
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p1,
            R.drawable.p6,
            R.drawable.p7
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        init()
    }

    private fun init() {
        imgBack.visibility = View.VISIBLE
        imgLike.visibility = View.VISIBLE
        flBadge.visibility = View.VISIBLE
        headerText.text = ""
        recyclerView = findViewById(R.id.recyclerView)
        viewPager = findViewById(R.id.viewPager)
        pageIndicatorView = findViewById(R.id.pageIndicatorView)
        setUpViewPager()
        tvOldPrice.text = "10,499"
        tvOldPrice.paintFlags = tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        val reviewAdapter = ReviewsAdapter(items2, true)
        recyclerView.adapter = reviewAdapter

        imgBack.setOnClickListener(this)
        lyMoreReview.setOnClickListener(this)
        flBadge.setOnClickListener(this)
        applyCard.setOnClickListener(this)
        bookNow.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bookNow -> {
                Utils.cartArray.clear()
                updateCartBadge()
            }
            R.id.applyCard -> {
                if (tvApplyCard.text.toString().lowercase() == "Go to Cart"){
                    openCart()
                }else {
                    Utils.addToCart()
                    tvApplyCard.text = "Go to Cart"
                    updateCartBadge()
                }
            }
            R.id.imgBack -> {
                onBackPressed()
            }
            R.id.imgLike -> {

            }
            R.id.flBadge -> {
                openCart()
            }
            R.id.lyMoreReview -> {
                val intent = Intent(this, ReviewActivity::class.java)
                startActivity(intent)
            }
            else -> {
            }
        }
    }

    private fun openCart() {
        val intent = Intent(this, MyCartActivity::class.java)
        startActivity(intent)
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

    private fun setUpViewPager() {
        customAdapter = CustomAdapter(this, imageId)
        viewPager.adapter = customAdapter
        pageIndicatorView.setCount(customAdapter.count)
        pageIndicatorView.setSelection(0)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                pageIndicatorView.setSelection(position)
            }
        })
    }

}