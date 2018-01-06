package website.grahamearley.placefinder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Adapter for displaying lists of Venue cards.
 */
class VenueRecyclerAdapter : RecyclerView.Adapter<VenueViewHolder>() {

    private var venues: List<VenueItem> = emptyList()

    fun setVenues(venues: List<VenueItem>) {
        this.venues = venues
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = venues.size

    fun isEmpty(): Boolean = venues.isEmpty()

    override fun onBindViewHolder(holder: VenueViewHolder?, position: Int) {
        val venue = venues[position].venue
        holder?.apply {
            val photo = venue?.getFirstFeaturedPhotoOrNull()

            photo?.let {
                venueImageView.loadImage(it.getUrl())
                venueImageView.show()
            } ?: venueImageView.hide()

            titleTextView.text = venue?.name
            addressTextView.text = venue?.getStreetAddressOrNull()
            categoryTextView.text = venue?.getFirstCategoryOrNull()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VenueViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_venue, parent, false)
        return VenueViewHolder(itemView)
    }
}

