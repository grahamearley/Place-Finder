package website.grahamearley.placefinder.ui.list.contract

import website.grahamearley.placefinder.FoursquareResponse
import website.grahamearley.placefinder.VenueItem
import website.grahamearley.placefinder.data.FoursquareInteractorContract

/**
 * Interface for the Place List presenter.
 */
interface PlaceListPresenterContract {
    val view: PlaceListViewContract
    val interactor: FoursquareInteractorContract

    fun onNewVenueQuery(query: String, near: String)
    fun onVenueItemClicked(venueItem: VenueItem)
    fun onVenuesLoaded(foursquareResponse: FoursquareResponse?)
    fun onVenuesRequestError(throwable: Throwable)
}