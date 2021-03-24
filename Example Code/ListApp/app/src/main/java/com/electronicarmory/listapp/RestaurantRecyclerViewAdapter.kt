package com.electronicarmory.listapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.electronicarmory.listapp.dummy.RestaurantRepository.RestaurantItem

/**
 * [RecyclerView.Adapter] that can display a [RestaurantItem].
 * TODO: Replace the implementation with code for your data type.
 */
class RestaurantRecyclerViewAdapter(
    private val values: List<RestaurantItem>
) : RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = "${item.content} XYZ"
        holder.imageView.setImageResource(R.drawable.ic_launcher_background)

        holder.button.tag = position
        holder.button.setOnClickListener {
            Log.d("MZ", "TAG: ${it.tag}")
            Log.d("MZ", "Content: ${values[position].content}")
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val button: Button = view.findViewById(R.id.button)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}