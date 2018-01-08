package website.grahamearley.placefinder.detail.contract

import website.grahamearley.placefinder.Tip

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

    fun setRating(rating: String)
    fun setRatingColor(color: String)
    fun showRating()
    fun hideRating()

    fun setMenuUrl(url: String)
    fun showMenuButton()
    fun hideMenuButton()

    fun setPhoneNumber(phoneNumber: String)
    fun showPhoneButton()
    fun hidePhoneButton()

    fun setWebsite(url: String)
    fun showWebsiteButton()
    fun hideWebsiteButton()
}