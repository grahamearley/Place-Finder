package website.grahamearley.placefinder.ui.detail.contract

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

    fun loadPhotos(venueId: String)
}