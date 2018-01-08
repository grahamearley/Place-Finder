package website.grahamearley.placefinder.detail

import website.grahamearley.placefinder.VenueItem
import website.grahamearley.placefinder.detail.contract.PlaceDetailPresenterContract
import website.grahamearley.placefinder.detail.contract.PlaceDetailViewContract

/**
 * Presenter implementation for the Venue detail view.
 */
class PlaceDetailPresenter(override val view: PlaceDetailViewContract) : PlaceDetailPresenterContract {
    override var venueItem: VenueItem? = null

    private val reason get() = venueItem?.reasons?.items?.firstOrNull()
    private val images get() = venueItem?.venue?.getPhotoUrlsOrNull()
    private val tips get() = venueItem?.tips
    private val name get() = venueItem?.venue?.name
    private val address get() = venueItem?.venue?.getStreetAddressOrNull()
    private val category get() = venueItem?.venue?.getFirstCategoryOrNull()
    private val rating get() = venueItem?.venue?.rating
    private val ratingColor get() = venueItem?.venue?.ratingColor
    private val menuUrl get() = venueItem?.venue?.menu?.mobileUrl
    private val phoneNumber get() = venueItem?.venue?.contact?.phone
    private val website get() = venueItem?.venue?.url

    override fun onViewCreated() {

    }

    override fun onMenuButtonClicked() {

    }

    override fun onPhoneButtonClicked() {

    }

    override fun onWebsiteButtonClicked() {

    }
}