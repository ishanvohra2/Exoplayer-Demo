package com.ishanvohra.exoplayerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.ishanvohra.exoplayerdemo.adapter.PlayerFragmentAdapter

class MainActivity : AppCompatActivity() {

    private var viewPager: ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager2)

        val mediaList = ArrayList<String>()
        mediaList.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8")
        mediaList.add("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
        mediaList.add("https://fcc3ddae59ed.us-west-2.playback.live-video.net/api/video/v1/us-west-2.893648527354.channel.YtnrVcQbttF0.m3u8")
        mediaList.add("https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8")
        mediaList.add("https://cph-p2p-msl.akamaized.net/hls/live/2000341/test/master.m3u8")

        val adapter = PlayerFragmentAdapter(this, mediaList)
        viewPager?.adapter = adapter

        //set orientation to vertical
        viewPager?.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
}