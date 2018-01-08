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
        reason?.summary?.let { summary ->
            view.setReason(summary)
            view.showReason()
        } ?: view.hideReason()

        images?.let { images ->
            if (images.isEmpty()) {
                view.hideVenueImages()
            } else {
                view.setVenueImages(images)
                view.showVenueImages()
            }
        } ?: view.hideVenueImages()

        tips?.let { tips ->
            if (tips.isEmpty()) {
                view.hideVenueTips()
            } else {
                view.setVenueTips(tips)
                view.showVenueTips()
            }
        } ?: view.hideVenueTips()

        name?.let { name ->
            view.setVenueName(name)
            view.showVenueName()
        } ?: view.hideVenueName()

        address?.let { address ->
            view.setVenueAddress(address)
            view.showVenueAddress()
        } ?: view.hideVenueAddress()

        category?.let { category ->
            view.setVenueCategory(category)
            view.showVenueCategory()
        } ?: view.hideVenueCategory()

        rating?.let { rating ->
            view.setRating(rating)
            view.setRatingColor(ratingColor)
            view.showRating()
        } ?: view.hideRating()

        view.setOnClickListeners()

        menuUrl?.let { menuUrl ->
            view.setMenuUrl(menuUrl)
            view.showMenuButton()
        } ?: view.hideMenuButton()

        phoneNumber?.let { phoneNumber ->
            view.setPhoneNumber(phoneNumber)
            view.showPhoneButton()
        } ?: view.hidePhoneButton()

        website?.let { website ->
            view.setWebsite(website)
            view.showWebsiteButton()
        } ?: view.hideWebsiteButton()
    }

    override fun onMenuButtonClicked() {
        menuUrl?.let { menuUrl ->
            view.launchUrl(menuUrl)
        } ?: view.showMenuNotAvailableSnackbar()
    }

    override fun onPhoneButtonClicked() {
        phoneNumber?.let { phoneNumber ->
            view.launchDialer(phoneNumber)
        } ?: view.showPhoneNumberNotAvailableSnackbar()
    }

    override fun onWebsiteButtonClicked() {
        website?.let { website ->
            view.launchUrl(website)
        } ?: view.showWebsiteNotAvailableSnackbar()
    }
}