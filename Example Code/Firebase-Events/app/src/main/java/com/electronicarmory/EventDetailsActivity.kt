package com.electronicarmory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.electronicarmory.controllers.EventsController
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.content_event_details.*
import java.text.SimpleDateFormat

class EventDetailsActivity : AppCompatActivity() {

    var bsuEventPosition:Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        setSupportActionBar(toolbar)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        supportActionBar?.title = "Event"
        bsuEventPosition = intent.getIntExtra("BSUEventPosition", - 1)

        if( bsuEventPosition != -1 ) {
            val bsuEvent = EventsController.eventsArray[bsuEventPosition]

            eventTitleTextView.text = bsuEvent.eventTitle
            eventHostedByTextView.text = "Hosted by: ${bsuEvent.eventHost}"

            val pattern = "E MMMM dd @ hh:mm a"
            val simpleDateFormat = SimpleDateFormat(pattern)

            val date = simpleDateFormat.format(bsuEvent.eventDate.toDate())
            eventDateTextView.text = date

            eventLocationTextView.text = bsuEvent.eventLocation
            eventDescriptionTextView.text = bsuEvent.eventDescription
        }
    }
}
