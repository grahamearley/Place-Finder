package website.grahamearley.placefinder.detail

import website.grahamearley.placefinder.VenueItem
import website.grahamearley.placefinder.detail.contract.PlaceDetailPresenterContract
import website.grahamearley.placefinder.detail.contract.PlaceDetailViewContract

/**
 * Presenter implementation for the Venue detail view.
 */
class PlaceDetailPresenter(override val view: PlaceDetailViewContract) : PlaceDetailPresenterContract {
    override var venueItem: VenueItem? = null

    override fun onViewCreated() {

    }

    override fun onMenuButtonClicked() {

    }

    override fun onPhoneButtonClicked() {

    }

    override fun onWebsiteButtonClicked() {

    }
}