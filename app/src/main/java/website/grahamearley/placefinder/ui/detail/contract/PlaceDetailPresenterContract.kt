package website.grahamearley.placefinder.ui.detail.contract

import website.grahamearley.placefinder.FoursquareResponse
import website.grahamearley.placefinder.Tip
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
    fun onTipsLoaded(tips: List<Tip>)
    fun onTipsRequestError(throwable: Throwable)

    fun loadPhotos(venueId: String)
    fun onPhotosLoaded(photoUrls: List<String>)
    fun onPhotosRequestError(throwable: Throwable)
}