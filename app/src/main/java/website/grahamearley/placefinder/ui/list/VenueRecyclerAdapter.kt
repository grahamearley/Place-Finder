package website.grahamearley.placefinder.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import website.grahamearley.placefinder.*
import website.grahamearley.placefinder.extension.hide
import website.grahamearley.placefinder.extension.loadImage
import website.grahamearley.placefinder.extension.show
import website.grahamearley.placefinder.model.VenueItem

/**
 * Adapter for displaying lists of Venue cards.
 */
class VenueRecyclerAdapter : RecyclerView.Adapter<VenueViewHolder>() {

    var venues: List<VenueItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onItemClicked: (VenueItem) -> Unit = {}

    override fun getItemCount(): Int = venues.size

    fun isEmpty(): Boolean = venues.isEmpty()

    override fun onBindViewHolder(holder: VenueViewHolder?, position: Int) {
        val venueItem = venues[position]
        val venue = venueItem.venue

        holder?.apply {
            val photo = venue?.getFirstFeaturedPhotoOrNull()

            photo?.let {
                venueImageView.loadImage(it.getUrl())
                venueImageView.show()
            } ?: venueImageView.hide()

            titleTextView.text = venue?.name
            addressTextView.text = venue?.getStreetAddressOrNull()
            categoryTextView.text = venue?.getFirstCategoryOrNull()

            itemView.setOnClickListener { onItemClicked(venueItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VenueViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_venue, parent, false)
        return VenueViewHolder(itemView)
    }

    fun setOnVenueClickListener(onItemClicked: (VenueItem) -> Unit) {
        this.onItemClicked = onItemClicked
    }
}

