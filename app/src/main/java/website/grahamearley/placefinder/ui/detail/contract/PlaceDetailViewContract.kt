package website.grahamearley.placefinder.ui.detail.contract

import website.grahamearley.placefinder.model.Tip

/**
 * Interface for the Place Detail view.
 */
interface PlaceDetailViewContract {
    val presenter: PlaceDetailPresenterContract

    fun setReason(reason: String)
    fun showReason()
    fun hideReason()

    fun setVenueImages(urls: List<String>)
    fun showVenueImages()
    fun hideVenueImages()

    fun setVenueTips(tips: List<Tip>)
    fun showVenueTips()
    fun hideVenueTips()

    fun setVenueName(name: String)
    fun showVenueName()
    fun hideVenueName()

    fun setVenueAddress(address: String)
    fun showVenueAddress()
    fun hideVenueAddress()

    fun setVenueCategory(category: String)
    fun showVenueCategory()
    fun hideVenueCategory()

    fun setRating(rating: Double)
    fun setRatingColor(color: String?)
    fun showRating()
    fun hideRating()

    fun showMenuButton()
    fun hideMenuButton()

    fun showPhoneButton()
    fun hidePhoneButton()

    fun showWebsiteButton()
    fun hideWebsiteButton()

    fun setOnClickListeners()

    fun launchUrl(url: String)
    fun launchDialer(phoneNumber: String)

    fun showMenuNotAvailableError()
    fun showPhoneNumberNotAvailableError()
    fun showWebsiteNotAvailableError()
}