package website.grahamearley.placefinder.ui.list.contract

import android.support.annotation.StringRes
import website.grahamearley.placefinder.model.VenueItem

/**
 * Interface for the Place List view.
 */
interface PlaceListViewContract {
    val presenter: PlaceListPresenterContract

    fun showListItems()
    fun hideListItems()
    fun setListItems(venues: List<VenueItem>)

    fun showStatusText()
    fun hideStatusText()
    fun setStatusText(@StringRes stringRes: Int)
    fun setStatusText(text: String)

    fun onSearchButtonClick()

    fun showProgressBar()
    fun hideProgressBar()

    fun setSearchBarGravityToCenter()
    fun setSearchBarGravityToBottom()

    fun launchVenueDetailView(venueItem: VenueItem)
}