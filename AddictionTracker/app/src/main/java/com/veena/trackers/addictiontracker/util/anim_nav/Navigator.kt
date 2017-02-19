package com.veena.trackers.addictiontracker.util.anim_nav

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Created by veena on 16/2/17.
 */

object Navigator {

    fun switchFragment(activity: AppCompatActivity, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
                .add(android.R.id.content, fragment).commitAllowingStateLoss()
    }

    fun switchFragmentAddtoStack(activity: AppCompatActivity, fragment: Fragment, tag: String) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }

    fun switchActivity(context: AppCompatActivity, activity: Class<*>, finishCallingActivity: Boolean) {
        val intent = Intent(context, activity)
        context.startActivity(intent)
        if (finishCallingActivity) {
            context.finish()
        }
    }

   /* fun switchActivity(context: AppCompatActivity, activity: Class<*>, bundle: Bundle, finishCallingActivity: Boolean) {
        val intent = Intent(context, activity)
        intent.putExtra("data", bundle)
        context.startActivity(intent)
        if (finishCallingActivity) {
            context.finish()
        }
    }*/
}
