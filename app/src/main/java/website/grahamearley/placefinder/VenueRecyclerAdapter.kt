package website.grahamearley.placefinder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Adapter for displaying lists of Venue cards.
 */
class VenueRecyclerAdapter(private val venues: List<VenueItem>) : RecyclerView.Adapter<VenueViewHolder>() {

    override fun getItemCount(): Int = venues.size

    override fun onBindViewHolder(holder: VenueViewHolder?, position: Int) {
        val venue = venues[position].venue
        holder?.apply {
            venueImageView.hide() // todo: show pics! with picasso

            titleTextView.text = venue.name
            addressTextView.text = venue.location.formattedAddress.firstOrNull()
            categoryTextView.text = venue.categories.firstOrNull()?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VenueViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_venue, parent, false)
        return VenueViewHolder(itemView)
    }
}

