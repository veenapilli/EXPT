package com.veena.trackers.addictiontracker.home

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import com.veena.trackers.addictiontracker.R
import com.veena.trackers.addictiontracker.util.anim_nav.Navigator

class SplashActivity : AppCompatActivity() {
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mHandler.postDelayed(Runnable {
           Navigator.switchActivity(this, HomeActivity::class.java, true);
        }, 5000);
    }
}
