package com.electronicarmory.controllers

import com.google.firebase.Timestamp
import java.io.Serializable

data class BSUEvent(
    var eventTitle: String,
    var eventDescription: String,
    var eventHost: String,
    var eventDate: Timestamp,
    var eventLocation: String
) : Serializable {

}
