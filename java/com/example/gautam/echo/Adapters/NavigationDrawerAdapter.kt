package com.example.gautam.echo.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.gautam.echo.R
import com.example.gautam.echo.activities.MainActivity
import com.example.gautam.echo.fragments.AboutUsFragment
import com.example.gautam.echo.fragments.FavouriteFragment
import com.example.gautam.echo.fragments.MainScreenFragment
import com.example.gautam.echo.fragments.SettingFragment

class NavigationDrawerAdapter(_contentList: ArrayList<String>,_getImages: IntArray,_context: Context):
    RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>(){
    var contentList: ArrayList<String>?=null
    var getImage: IntArray?=null
    var mContext:Context?=null
    init {
        this.contentList=_contentList
        this.getImage=_getImages
        this.mContext=_context
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NavViewHolder {
        var itemView=LayoutInflater.from(p0.context)
            .inflate(R.layout.row_custom_navigationdrawer,p0,false)
        val returnThis=NavViewHolder(itemView)
        return returnThis
    }

    override fun getItemCount(): Int {
        return contentList?.size as Int
    }
    override fun onBindViewHolder(p0: NavViewHolder, p1: Int) {
        p0?.icon_GET?.setBackgroundResource(getImage?.get(p1)as Int)
        p0?.text_GET?.setText(contentList?.get(p1))
        p0?.contentHolder?.setOnClickListener({
            if(p1==0) {
                val mainScreenFragment = MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment,mainScreenFragment)
                    .commit()
            }else if(p1==1) {
                val favouriteFragment = FavouriteFragment()
                (mContext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, favouriteFragment)
                    .commit()
            }else if(p1==2){
                val settingFragment = SettingFragment()
                (mContext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment,settingFragment)
                    .commit()
            }else{
                val aboutUsFragment = AboutUsFragment()
                (mContext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, aboutUsFragment)
                    .commit()
            }
            MainActivity.Statified.drawerLayout?.closeDrawers()
        })
    }

    class NavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon_GET: ImageView?=null
        var text_GET: TextView?=null
        var contentHolder: RelativeLayout?=null
        init {
            icon_GET=itemView?.findViewById(R.id.icon_navdrawer)
            text_GET=itemView?.findViewById(R.id.text_navdrawer)
            contentHolder=itemView?.findViewById(R.id.navdrawer_item_content_holder)
        }

    }
}