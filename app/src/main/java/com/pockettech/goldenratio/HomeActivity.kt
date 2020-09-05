package com.pockettech.goldenratio

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem: MenuItem? = null
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerLayout = findViewById(R.id.drawerlayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationview)
        setUpToolbar()
        openHome()
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@HomeActivity, drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            //it.isChecked = true

            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false

            }
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it
            when (it.itemId) {
                R.id.home -> {
                    openHome()
                    drawerLayout.closeDrawers()


                }
                R.id.favourites ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        universeFragment()
                    ).commit()
                    supportActionBar?.title="Golden Ratio Everywhere"
                    drawerLayout.closeDrawers()
                }
                R.id.myprofile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        ArtDesign()
                    ).commit()
                    supportActionBar?.title="Art & Design"
                    drawerLayout.closeDrawers()

                }
                R.id.orderhistory ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        Architecture()
                    ).commit()
                    supportActionBar?.title="In Photography"
                    drawerLayout.closeDrawers()

                }
                R.id.faq -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        Interactive()
                    ).commit()
                    supportActionBar?.title="Golden Rectangle"
                    drawerLayout.closeDrawers()

                }
            }
            return@setNavigationItemSelectedListener true
        }
    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Introduction"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }
    fun openHome(){
        val fragment=HomeFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
        supportActionBar?.title="Introduction"
        navigationView.setCheckedItem(R.id.home)



    }
    override fun onBackPressed() {
        val flag=supportFragmentManager.findFragmentById(R.id.frame)
        when(flag){
            !is HomeFragment ->openHome()
            else->finishAffinity()
        }
    }

}

