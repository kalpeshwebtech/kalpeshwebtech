package com.example.flipcart

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
import com.example.flipcart.adapter.NavigationRVAdapter
import com.example.flipcart.fragment.HomeFragment
import com.example.flipcart.model.NavigationItemModel
import com.example.flipcart.utils.ClickListener
import com.example.flipcart.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    var homeFragment: HomeFragment? = null
    var activeFragment: Fragment? = null
    private var items = arrayListOf(
        NavigationItemModel(R.drawable.ic_home, "Home", false),
        NavigationItemModel(R.drawable.ic_music, "Music", true),
        NavigationItemModel(R.drawable.ic_movie, "Movies", false),
        NavigationItemModel(R.drawable.ic_book, "Books", false),
        NavigationItemModel(R.drawable.ic_profile, "Profile", true),
        NavigationItemModel(R.drawable.ic_settings, "Settings", true),
        NavigationItemModel(R.drawable.ic_social, "Like us on facebook", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        updateAdapter(0)
        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

    }

    fun init() {
        drawerLayout = findViewById(R.id.drawer_layout)
        //setSupportActionBar(activity_main_toolbar)
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
                when (position) {
                    0 -> {
//                        val homeFragment = DemoFragment()
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.activity_main_content_id, homeFragment).commit()
                        headerText.text = "Home"
                    }
                    1 -> {
                        headerText.text = "Music"
//                        val musicFragment = DemoFragment()
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.activity_main_content_id, musicFragment).commit()
                    }
                    2 -> {
                        headerText.text = "Movies"
//                        val moviesFragment = DemoFragment()
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.activity_main_content_id, moviesFragment).commit()
                    }
                    3 -> {
                        headerText.text = "Books"
//                        val booksFragment = DemoFragment()
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.activity_main_content_id, booksFragment).commit()
                    }
                    4 -> {
                        headerText.text = "Profile"
//                        val intent = Intent(this@MainActivity, DemoActivity::class.java)
//                        startActivity(intent)
                    }
                    5 -> {
                        headerText.text = "Settings"
//                        val settingsFragment = DemoFragment()
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.activity_main_content_id, settingsFragment).commit()
                    }
                    6 -> {
                        headerText.text = "Like us on Facebook"
//                        val uri: Uri = Uri.parse("https://johnc.co/fb")
//                        val intent = Intent(Intent.ACTION_VIEW, uri)
//                        startActivity(intent)
                    }
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