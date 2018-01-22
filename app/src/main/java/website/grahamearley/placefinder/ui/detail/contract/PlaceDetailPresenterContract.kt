package website.grahamearley.placefinder.ui.detail.contract

import website.grahamearley.placefinder.FoursquareResponse
import website.grahamearley.placefinder.VenueItem

/**
 * Interface for the Place Detail presenter.
 */
interface PlaceDetailPresenterContract {
    val view: PlaceDetailViewContract
    var venueItem: VenueItem?

    fun onVenueItemSet()

    fun onMenuButtonClicked()

    fun onPhoneButtonClicked()

    fun onWebsiteButtonClicked()

    fun loadTips(venueId: String)
    fun onTipsLoaded(foursquareResponse: FoursquareResponse)
    fun onTipsRequestError(throwable: Throwable)

    fun loadPhotos(venueId: String)
    fun onPhotosLoaded(foursquareResponse: FoursquareResponse)
    fun onPhotosRequestError(throwable: Throwable)
}