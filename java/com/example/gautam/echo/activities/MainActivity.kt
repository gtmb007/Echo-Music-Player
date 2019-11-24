package com.example.gautam.echo.activities

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.gautam.echo.Adapters.NavigationDrawerAdapter
import com.example.gautam.echo.R
import com.example.gautam.echo.fragments.MainScreenFragment


class MainActivity : AppCompatActivity() {
    var image_for_navDrawer= intArrayOf(R.drawable.navigation_allsongs,R.drawable.navigation_favorites,R.drawable.navigation_settings,R.drawable.navigation_aboutus)
    var navigationDrawerIconList: ArrayList<String> = arrayListOf()
    object Statified{
        var drawerLayout: DrawerLayout?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        MainActivity.Statified.drawerLayout=findViewById(R.id.drawer_layout)
        navigationDrawerIconList.add("All Song")
        navigationDrawerIconList.add("Favourite")
        navigationDrawerIconList.add("Setting")
        navigationDrawerIconList.add("About Us")
        val toogle= ActionBarDrawerToggle(this@MainActivity, MainActivity.Statified.drawerLayout,toolbar,
            R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        MainActivity.Statified.drawerLayout?.addDrawerListener(toogle)
        toogle.syncState()
        val mainScreenFragment= MainScreenFragment()
        this.supportFragmentManager
            .beginTransaction()
            .add(R.id.details_fragment,mainScreenFragment,"MainScreenFragment")
            .commit()
        var _navigationAdapter= NavigationDrawerAdapter(navigationDrawerIconList,image_for_navDrawer,this)
        _navigationAdapter.notifyDataSetChanged()

        var navigation_recycler_view=findViewById<RecyclerView>(R.id.navigation_recycler_view)
        navigation_recycler_view.layoutManager= LinearLayoutManager(this)
        navigation_recycler_view.itemAnimator= DefaultItemAnimator()
        navigation_recycler_view.adapter=_navigationAdapter
        navigation_recycler_view.setHasFixedSize(true)
    }

    override fun onStart() {
        super.onStart()
    }
}
