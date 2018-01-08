package website.grahamearley.placefinder.detail.contract

import website.grahamearley.placefinder.VenueItem

/**
 * Interface for the Place Detail presenter.
 */
interface PlaceDetailPresenterContract {
    val view: PlaceDetailViewContract
    var venueItem: VenueItem?

    fun onViewCreated()

    fun onMenuButtonClicked()

    fun onPhoneButtonClicked()

    fun onWebsiteButtonClicked()
}