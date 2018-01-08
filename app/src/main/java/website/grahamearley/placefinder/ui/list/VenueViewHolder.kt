package website.grahamearley.placefinder.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item_venue.view.*

class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val venueImageView: ImageView = itemView.venueImageView
    val titleTextView: TextView = itemView.titleTextView
    val addressTextView: TextView = itemView.addressTextView
    val categoryTextView: TextView = itemView.categoryTextView
}