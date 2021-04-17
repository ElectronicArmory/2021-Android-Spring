package com.electronicarmory.controllers

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.electronicarmory.EventMessage
import org.greenrobot.eventbus.EventBus
import java.util.*
import kotlin.collections.ArrayList

object EventsController {


    private var firebaseDB = FirebaseFirestore.getInstance()

    var eventsArray = ArrayList<BSUEvent>()


    fun refreshEvents(){
        firebaseDB.collection("events").orderBy("date").whereGreaterThan("date", Date())
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if( firebaseFirestoreException != null){

                }
                else if (querySnapshot != null) {
                    //Manage our documentSnapshot
                    Log.d("BSU", querySnapshot.toString())
                    if( querySnapshot.documents.isNotEmpty() ) {
                        eventsArray = ArrayList<BSUEvent>()
                        for (document in querySnapshot.documents) {
                            Log.d("BSU", document.data.toString())
                            if (document.data != null) {

                                val descriptionString = document.data!!["description"] as String
                                val descriptionStringFormatted = descriptionString.replace("\\n", "\n", true)

                                val bsuLocation = document.data!!["location"] as Map<*, *>
                                val locationName = bsuLocation["locationName"] as String
                                val street = bsuLocation["addressOne"] as String
                                val streetTwo = bsuLocation["addressTwo"] as String
                                val city = bsuLocation["city"] as String
                                val state = bsuLocation["state"] as String
                                val zip = bsuLocation["zip"] as String

                                var location = "${locationName}\n"

                                if( street.isNotEmpty()) {
                                    location = location.plus("${ street }\n")
                                }

                                if (streetTwo.isNotEmpty()){
                                    location = location.plus("${streetTwo}\n")
                                }

                                if( city.isNotEmpty() ){
                                    location = location.plus("${city}, ")
                                }

                                if( state.isNotEmpty() ) {
                                    location = location.plus(state)
                                }

                                if( zip.isNotEmpty()){
                                    location = location.plus(zip)
                                }


                                val newEvent = BSUEvent(
                                    document.data!!["topic"] as String,
                                    descriptionStringFormatted,
                                    document.data!!["hostedBy"] as String,
                                    document.data!!["date"] as Timestamp,
                                    location
                                )
                                eventsArray.add(newEvent)
                            }
                        }
                        EventBus.getDefault().post(EventMessage())
                    }
                }
            }
    }
}