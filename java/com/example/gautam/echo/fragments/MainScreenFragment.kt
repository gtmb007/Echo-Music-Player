package com.example.gautam.echo.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.gautam.echo.Adapters.MainScreenAdapter
import com.example.gautam.echo.R
import com.example.gautam.echo.Songs



class MainScreenFragment : Fragment() {
    var getSongsList: ArrayList<Songs>?=null
    var nowPlayingBottomBar: RelativeLayout?=null
    var playPauseBotton: ImageButton?=null
    var songTitle: TextView?=null
    var visibleLayout: RelativeLayout?=null
    var noSongs: RelativeLayout?=null
    var recyclerView: RecyclerView?=null
    var myActivity: Activity?=null
    var _mainScreenAdapter:MainScreenAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater!!.inflate(R.layout.fragment_main_screen,container,false)
        visibleLayout=view?.findViewById<RelativeLayout>(R.id.visibleLayout)
        noSongs=view?.findViewById<RelativeLayout>(R.id.noSongs)
        nowPlayingBottomBar=view?.findViewById<RelativeLayout>(R.id.hiddenBarMainScreen)
        songTitle=view?.findViewById<TextView>(R.id.songTitleMainScreen)
        playPauseBotton=view?.findViewById<ImageButton>(R.id.playPauseButton)
        recyclerView=view?.findViewById<RecyclerView>(R.id.contentMain)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getSongsList=getSongsFromPhone()
        _mainScreenAdapter= MainScreenAdapter(getSongsList as ArrayList<Songs>,myActivity as Context)
        val mLayoutManager=LinearLayoutManager(myActivity)
        recyclerView?.layoutManager=mLayoutManager
        recyclerView?.itemAnimator=DefaultItemAnimator()
        recyclerView?.adapter=_mainScreenAdapter

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        myActivity=context as Activity
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        myActivity=activity
    }
    fun getSongsFromPhone(): ArrayList<Songs>{
        var arrayList=ArrayList<Songs>()
        var contentResolver=myActivity?.contentResolver
        var songUri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        var songCruser=contentResolver?.query(songUri,null,null,null,null)
        if(songCruser!=null && songCruser.moveToFirst()){
            val songId=songCruser.getColumnIndex(MediaStore.Audio.Media._ID)
            val songTitle=songCruser.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val songArtist=songCruser.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val songData=songCruser.getColumnIndex(MediaStore.Audio.Media.DATA)
            val dateIndex=songCruser.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED)
            while (songCruser.moveToNext()){
                var currentId=songCruser.getLong(songId)
                var currentTitle=songCruser.getString(songTitle)
                var currentArtist=songCruser.getString(songArtist)
                var currentData=songCruser.getString(songData)
                var currentDate=songCruser.getLong(dateIndex)
                arrayList.add(Songs(currentId,currentTitle,currentArtist,currentData,currentDate))
            }
        }
        return arrayList
    }
}
