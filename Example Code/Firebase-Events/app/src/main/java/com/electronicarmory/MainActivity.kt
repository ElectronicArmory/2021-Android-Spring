package com.electronicarmory

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import com.electronicarmory.controllers.EventsController
import com.electronicarmory.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class MainActivity : AppCompatActivity(), EventsFragment.OnListFragmentInteractionListener {


    private lateinit var eventsFragment: EventsFragment



    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_events -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, eventsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_people -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        eventsFragment = EventsFragment()

        EventsController.refreshEvents()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, eventsFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

//        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }


    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }


    @Subscribe
    fun onEvent(message: EventMessage) {

    }


    override fun onListFragmentInteraction(eventPosition: Int?) {
        val eventDetailsActivityIntent = Intent(this, EventDetailsActivity::class.java)
        eventDetailsActivityIntent.putExtra("BSUEventPosition", eventPosition)
        startActivity(eventDetailsActivityIntent)
    }
}
