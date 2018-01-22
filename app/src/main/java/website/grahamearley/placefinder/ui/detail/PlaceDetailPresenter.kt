package website.grahamearley.placefinder.ui.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import website.grahamearley.placefinder.VenueItem
import website.grahamearley.placefinder.data.FoursquareInteractor
import website.grahamearley.placefinder.data.FoursquareInteractorContract
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailPresenterContract
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailViewContract

/**
 * Presenter implementation for the Venue detail view.
 */
class PlaceDetailPresenter(override val view: PlaceDetailViewContract,
                           private val interactor: FoursquareInteractorContract = FoursquareInteractor())
    : PlaceDetailPresenterContract {

    override var venueItem: VenueItem? = null
        set(item) {
            field = item
            onVenueItemSet()
        }

    private val reason get() = venueItem?.reasons?.items?.firstOrNull()
    private val name get() = venueItem?.venue?.name
    private val address get() = venueItem?.venue?.getStreetAddressOrNull()
    private val category get() = venueItem?.venue?.getFirstCategoryOrNull()
    private val rating get() = venueItem?.venue?.rating
    private val ratingColor get() = venueItem?.venue?.ratingColor
    private val menuUrl get() = venueItem?.venue?.menu?.url
    private val phoneNumber get() = venueItem?.venue?.contact?.phone
    private val website get() = venueItem?.venue?.url

    /**
     * When the venue item is set, display the venue
     *  information in the UI (or hide views when
     *  info isn't available).
     */
    override fun onVenueItemSet() {
        val venueId = venueItem?.venue?.id
        if (venueId != null) {
            loadTips(venueId)
            loadPhotos(venueId)
        } else {
            view.hideVenueTips()
            view.hideVenueImages()
        }

        reason?.summary?.let { summary ->
            view.setReason(summary)
            view.showReason()
        } ?: view.hideReason()

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

        menuUrl?.let {
            view.showMenuButton()
        } ?: view.hideMenuButton()

        phoneNumber?.let {
            view.showPhoneButton()
        } ?: view.hidePhoneButton()

        website?.let {
            view.showWebsiteButton()
        } ?: view.hideWebsiteButton()
    }

    override fun onMenuButtonClicked() {
        menuUrl?.let { menuUrl ->
            view.launchUrl(menuUrl)
        } ?: view.showMenuNotAvailableError()
    }

    override fun onPhoneButtonClicked() {
        phoneNumber?.let { phoneNumber ->
            view.launchDialer(phoneNumber)
        } ?: view.showPhoneNumberNotAvailableError()
    }

    override fun onWebsiteButtonClicked() {
        website?.let { website ->
            view.launchUrl(website)
        } ?: view.showWebsiteNotAvailableError()
    }

    override fun loadTips(venueId: String) {
        interactor.requestVenueTips(venueId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { foursquareResponse ->
                        val tips = foursquareResponse.response?.tips?.items

                        if (tips != null && tips.isNotEmpty()) {
                            view.setVenueTips(tips)
                            view.showVenueTips()
                        } else {
                            view.hideVenueTips()
                        }
                    },
                    onError = { view.hideVenueTips() }
                )
    }

    override fun loadPhotos(venueId: String) {
        interactor.requestVenuePhotos(venueId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { foursquareResponse ->
                        val photoUrls = foursquareResponse.response?.photos?.items?.map { it.getUrl() }

                        if (photoUrls != null && photoUrls.isNotEmpty()) {
                            view.setVenueImages(photoUrls)
                            view.showVenueImages()
                        } else {
                            view.hideVenueImages()
                        }
                    },
                    onError = {
                        view.hideVenueImages()
                    }
                )
    }
}