package com.electronicarmory

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.electronicarmory.controllers.BSUEvent


import com.electronicarmory.EventsFragment.OnListFragmentInteractionListener
import com.electronicarmory.R

import kotlinx.android.synthetic.main.fragment_events.view.*
import java.text.SimpleDateFormat

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class EventsRecyclerViewAdapter(
    public var mValues: List<BSUEvent>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Int
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_events, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mEventTitleTextView.text = item.eventTitle
        holder.mEventDescriptionTextView.text = item.eventDescription
        holder.mEventHostTextView.text = item.eventHost

        val pattern = "E MMMM dd @ hh:mm a"
        val simpleDateFormat = SimpleDateFormat(pattern)

        val date = simpleDateFormat.format(item.eventDate.toDate())


        holder.mEventDateTextView.text = date
        with(holder.mView) {
            tag = position
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mEventTitleTextView: TextView = mView.listEventTitle
        val mEventDescriptionTextView: TextView = mView.listEventDescription
        val mEventHostTextView: TextView = mView.listHostedBy
        val mEventDateTextView: TextView = mView.listEventDate

        override fun toString(): String {
            return super.toString() + " '" + mEventDescriptionTextView.text + "'"
        }
    }
}
