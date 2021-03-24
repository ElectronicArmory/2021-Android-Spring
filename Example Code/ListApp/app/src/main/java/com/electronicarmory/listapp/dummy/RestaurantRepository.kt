package com.electronicarmory.listapp.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object RestaurantRepository {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<RestaurantItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, RestaurantItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: RestaurantItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): RestaurantItem {
        return RestaurantItem("Restaurant Name", "Restaurant " + position, makeDetails(position), imageURL = "")
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class RestaurantItem(val id: String, val content: String, val details: String, val imageURL:String) {
        override fun toString(): String = "$content: -  $details."
    }
}