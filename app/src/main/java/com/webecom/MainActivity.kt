package com.webecom

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.webecom.activity.MyOrders
import com.webecom.adapter.NavigationRVAdapter
import com.webecom.fragment.HomeFragment
import com.webecom.model.NavigationItemModel
import com.webecom.utils.ClickListener
import com.webecom.utils.RecyclerTouchListener
import com.webecom.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    var homeFragment: HomeFragment? = null
    var activeFragment: Fragment? = null
    private var items = Utils.getNavigation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        updateAdapter(0)
        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

    }

    fun init() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)
        imgMenu.visibility = View.VISIBLE
        headerText.text = "Home"
        imgMenu.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                val title=items[position].title
                headerText.text = title
                if (title=="Music"){
                    headerText.text = title
//                   val homeFragment = DemoFragment()
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.activity_main_content_id, homeFragment).commit()
                }else if (title=="Movies"){
                    headerText.text = title
                }else if (title=="Books"){
                    headerText.text = title
                }else if (title=="My Orders"){
                    headerText.text = title
                    startActivity(Intent(this@MainActivity,MyOrders::class.java))
                }else if (title=="Profile"){
                    headerText.text = title
                }else if (title=="Settings"){
                    headerText.text = title
                }else if (title=="Like us on facebook"){
                    headerText.text = title
                }
                if (position != 6 && position != 4) {
                    updateAdapter(position)
                }
                Handler().postDelayed({
                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))
        changeFragment(R.id.home)
        bottomNavigation.setOnItemSelectedListener {
            changeFragment(it.itemId)
        }
    }
    private fun changeFragment(id: Int):Boolean{
        val transaction = supportFragmentManager.beginTransaction()
        when (id) {
            R.id.home -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    transaction.add(R.id.flFragment, homeFragment!!)
                } else {
                    transaction.show(homeFragment!!)
                }
                activeFragment = homeFragment
                activeFragment?.onResume()
            }
            R.id.person -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    transaction.add(R.id.flFragment, homeFragment!!)
                } else {
                    transaction.show(homeFragment!!)
                }
                activeFragment = homeFragment
                activeFragment?.onResume()
            }
            R.id.settings -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    transaction.add(R.id.flFragment, homeFragment!!)
                } else {
                    transaction.show(homeFragment!!)
                }
                activeFragment = homeFragment
                activeFragment?.onResume()
            }
        }
        transaction.commit()
      return true
    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}