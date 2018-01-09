package website.grahamearley.placefinder

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Response
import website.grahamearley.placefinder.data.FoursquareInteractorContract
import website.grahamearley.placefinder.ui.detail.PlaceDetailPresenter
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailViewContract

/**
 * Unit tests for the venue detail activity, testing the contract
 *  between presenter, interactor, and view.
 *
 *  The mocked view has stubbed methods that change boolean flags
 *   indicating view visibility, for verifying whether these views
 *   are visible or not.
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class VenueDetailUnitTests {

    private val mockedView = mock(PlaceDetailViewContract::class.java)
    private val presenter = PlaceDetailPresenter(mockedView)

    private var reasonIsVisible = false
    private var venueImagesAreVisible = false
    private var venueTipsAreVisible = false
    private var venueNameIsVisible = false
    private var venueAddressIsVisible = false
    private var venueCategoryIsVisible = false
    private var ratingIsVisible = false
    private var menuButtonIsVisible = false
    private var phoneButtonIsVisible = false
    private var websiteButtonIsVisible = false

    @Before
    fun stubViewMethods() {
        Mockito.`when`(mockedView.showReason()).then {
            reasonIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideReason()).then {
            reasonIsVisible = false
            null
        }

        Mockito.`when`(mockedView.showVenueTips()).then {
            venueTipsAreVisible = true
            null
        }

        Mockito.`when`(mockedView.hideVenueTips()).then {
            venueTipsAreVisible = false
            null
        }

        Mockito.`when`(mockedView.showVenueImages()).then {
            venueImagesAreVisible = true
            null
        }

        Mockito.`when`(mockedView.hideVenueImages()).then {
            venueImagesAreVisible = false
            null
        }

        Mockito.`when`(mockedView.showVenueName()).then {
            venueNameIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideVenueName()).then {
            venueNameIsVisible = false
            null
        }

        Mockito.`when`(mockedView.showVenueAddress()).then {
            venueAddressIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideVenueAddress()).then {
            venueAddressIsVisible = false
            null
        }

        Mockito.`when`(mockedView.showVenueCategory()).then {
            venueCategoryIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideVenueCategory()).then {
            venueCategoryIsVisible = false
            null
        }

        Mockito.`when`(mockedView.showRating()).then {
            ratingIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideRating()).then {
            ratingIsVisible = false
            null
        }

        Mockito.`when`(mockedView.showMenuButton()).then {
            menuButtonIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideMenuButton()).then {
            menuButtonIsVisible = false
            null
        }

        Mockito.`when`(mockedView.showPhoneButton()).then {
            phoneButtonIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hidePhoneButton()).then {
            phoneButtonIsVisible = false
            null
        }

        Mockito.`when`(mockedView.showWebsiteButton()).then {
            websiteButtonIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideWebsiteButton()).then {
            websiteButtonIsVisible = false
            null
        }
    }

    @Test
    fun showsReasonIfHasReason() {
        reasonIsVisible = false

        presenter.venueItem = VenueItem(
                reasons = Reasons(count = 1,
                        items = listOf(ReasonItem(summary = "Here's a reason"))))

        verify(mockedView).setReason("Here's a reason")
        verify(mockedView).showReason()

        assertTrue("Reason is visible when it exists.", reasonIsVisible)
    }

    @Test
    fun hidesReasonIfNoReason() {
        reasonIsVisible = true

        presenter.venueItem = VenueItem(
                reasons = Reasons(count = 0,
                        items = emptyList()))

        verify(mockedView).hideReason()

        assertFalse("Reason is not visible when it doesn't exist.", reasonIsVisible)
    }

    @Test
    fun hidesTipsAndImagesIfNoVenueId() {
        venueTipsAreVisible = false

        val noIdVenue = Venue()
        presenter.venueItem = VenueItem(venue = noIdVenue)

        verify(mockedView).hideVenueImages()
        verify(mockedView).hideVenueTips()

        assertFalse("Tips are not visible when there is no venue ID.", venueTipsAreVisible)
        assertFalse("Images are not visible when there is no venue ID.", venueImagesAreVisible)
    }

    @Test
    fun showsTipsIfHasTips() {
        venueTipsAreVisible = false

        val tipList = listOf(Tip(text = "Good coffee!"))
        val tips = Tips(items = tipList)

        val interactorWithTips = object: FoursquareInteractorContract {
            override fun getPlacesAsync(query: String, near: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getVenueTipsAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                val foursquareResponse = FoursquareResponse(response = Response(tips = tips))
                val retrofitResponse = retrofit2.Response.success(foursquareResponse)

                onResponse(retrofitResponse)
            }
        }

        val presenterWithDummyInteractor = PlaceDetailPresenter(mockedView, interactorWithTips)

        presenterWithDummyInteractor.loadTips("dummy venue ID")
        verify(mockedView).setVenueTips(tipList)
        verify(mockedView).showVenueTips()

        assertTrue("Tips are visible when they exist.", venueTipsAreVisible)
    }

    @Test
    fun hidesTipsIfNoTips() {
        venueTipsAreVisible = true

        val emptyTips = Tips(items = emptyList())

        val interactorWithNoTips = object: FoursquareInteractorContract {
            override fun getPlacesAsync(query: String, near: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getVenueTipsAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                val foursquareResponse = FoursquareResponse(response = Response(tips = emptyTips))
                val retrofitResponse = retrofit2.Response.success(foursquareResponse)

                onResponse(retrofitResponse)
            }
        }

        val presenterWithDummyInteractor = PlaceDetailPresenter(mockedView, interactorWithNoTips)

        presenterWithDummyInteractor.loadTips("dummy venue ID")
        verify(mockedView).hideVenueTips()

        assertFalse("Tips are not visible when there aren't any.", venueTipsAreVisible)
    }

    @Test
    fun hidesTipsIfRequestFails() {
        venueTipsAreVisible = true

        val failingInteractor = object: FoursquareInteractorContract {
            override fun getPlacesAsync(query: String, near: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getVenueTipsAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                onFailure(null)
            }
        }

        val presenterWithDummyInteractor = PlaceDetailPresenter(mockedView, failingInteractor)

        presenterWithDummyInteractor.loadTips("dummy venue ID")
        verify(mockedView).hideVenueTips()

        assertFalse("Tips are not visible when request fails.", venueTipsAreVisible)
    }

    @Test
    fun showsImagesIfHasImages() {
        venueImagesAreVisible = false

        val photoItem1 = PhotoItem(prefix = "photo.com/", suffix = "/hi.bmp")
        val url1 = "photo.com/cap300/hi.bmp"

        val photoItem2 = PhotoItem(prefix = "photo.biz/", suffix = "/pic.jpg")
        val url2 = "photo.biz/cap300/pic.jpg"

        val expectedUrls = listOf(url1, url2)

        val photos = Photos(count = 2, items = listOf(photoItem1, photoItem2))

        val interactorWithPhotos = object: FoursquareInteractorContract {
            override fun getPlacesAsync(query: String, near: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenueTipsAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                val foursquareResponse = FoursquareResponse(response = Response(photos = photos))
                val retrofitResponse = retrofit2.Response.success(foursquareResponse)

                onResponse(retrofitResponse)
            }
        }

        val presenterWithDummyInteractor = PlaceDetailPresenter(mockedView, interactorWithPhotos)
        presenterWithDummyInteractor.loadPhotos("venue ID!")

        verify(mockedView).setVenueImages(expectedUrls)
        verify(mockedView).showVenueImages()

        assertTrue("Images are visible when they exist.", venueImagesAreVisible)
    }

    @Test
    fun hidesImagesIfNoImages() {
        venueImagesAreVisible = true

        val emptyPhotos = Photos(count = 0, items = emptyList())

        val interactorWithNoPhotos = object: FoursquareInteractorContract {
            override fun getPlacesAsync(query: String, near: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenueTipsAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                val foursquareResponse = FoursquareResponse(response = Response(photos = emptyPhotos))
                val retrofitResponse = retrofit2.Response.success(foursquareResponse)

                onResponse(retrofitResponse)
            }
        }

        val presenterWithDummyInteractor = PlaceDetailPresenter(mockedView, interactorWithNoPhotos)
        presenterWithDummyInteractor.loadPhotos("venue ID!")

        verify(mockedView).hideVenueImages()

        assertFalse("Images are not visible when there are none.", venueImagesAreVisible)
    }

    @Test
    fun hidesImagesIfImagesAreNull() {
        venueImagesAreVisible = true

        val failingInteractor = object: FoursquareInteractorContract {
            override fun getPlacesAsync(query: String, near: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenueTipsAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                onFailure(null)
            }
        }

        val presenterWithDummyInteractor = PlaceDetailPresenter(mockedView, failingInteractor)
        presenterWithDummyInteractor.loadPhotos("venue ID!")

        verify(mockedView).hideVenueImages()

        assertFalse("Images are not visible when request fails.", venueImagesAreVisible)
    }

    @Test
    fun showsNameIfHasName() {
        venueNameIsVisible = false
        presenter.venueItem = VenueItem(venue = Venue(name = "Asha Dining Hall"))

        verify(mockedView).setVenueName("Asha Dining Hall")
        verify(mockedView).showVenueName()

        assertTrue("Name is visible when it exists.", venueNameIsVisible)
    }

    @Test
    fun hideNameIfNoName() {
        venueNameIsVisible = true
        presenter.venueItem = VenueItem(venue = Venue())

        verify(mockedView).hideVenueName()

        assertFalse("Name is not visible when it doesn't exist.", venueNameIsVisible)
    }

    @Test
    fun showsAddressIfHasAddress() {
        venueAddressIsVisible = false

        val location = Location(formattedAddress = listOf("address", "city"))
        presenter.venueItem = VenueItem(venue = Venue(location = location))

        verify(mockedView).setVenueAddress("address")
        verify(mockedView).showVenueAddress()

        assertTrue("Address is visible when it exists.", venueAddressIsVisible)
    }

    @Test
    fun hideAddressIfNoAddress() {
        venueAddressIsVisible = true

        val location = Location()
        presenter.venueItem = VenueItem(venue = Venue(location = location))

        verify(mockedView).hideVenueAddress()

        assertFalse("Address is not visible when it doesn't exist.", venueAddressIsVisible)
    }

    @Test
    fun showsCategoryIfHasCategory() {
        venueCategoryIsVisible = false

        val category = Category(name = "Coffee Shop")
        val categoryList = listOf(category)
        presenter.venueItem = VenueItem(venue = Venue(categories = categoryList))

        verify(mockedView).setVenueCategory("Coffee Shop")
        verify(mockedView).showVenueCategory()

        assertTrue("Category is visible when it exists.", venueCategoryIsVisible)
    }

    @Test
    fun hideCategoryIfNoCategory() {
        venueCategoryIsVisible = true

        presenter.venueItem = VenueItem(venue = Venue(categories = emptyList()))

        verify(mockedView).hideVenueCategory()

        assertFalse("Category is not visible when it doesn't exist.", venueCategoryIsVisible)
    }

    @Test
    fun showsRatingIfHasRating() {
        ratingIsVisible = false

        presenter.venueItem = VenueItem(venue = Venue(rating = 9.5))

        verify(mockedView).setRating(9.5)
        verify(mockedView).showRating()

        assertTrue("Rating is visible when it exists.", ratingIsVisible)
    }

    @Test
    fun hideRatingIfNoRating() {
        ratingIsVisible = true

        presenter.venueItem = VenueItem(venue = Venue())

        verify(mockedView).hideRating()

        assertFalse("Rating is not visible when it doesn't exist.", ratingIsVisible)
    }

    @Test
    fun showsMenuIfHasMenu() {
        menuButtonIsVisible = false

        val menu = Menu(url = "menu.com")
        presenter.venueItem = VenueItem(venue = Venue(menu = menu))

        verify(mockedView).showMenuButton()

        assertTrue("Menu button is visible when it exists.", menuButtonIsVisible)
    }

    @Test
    fun hidesMenuIfNoMenu() {
        menuButtonIsVisible = true

        presenter.venueItem = VenueItem(venue = Venue())

        verify(mockedView).hideMenuButton()

        assertFalse("Menu button is not visible when it doesn't exist.", menuButtonIsVisible)
    }

    @Test
    fun showsPhoneIfHasPhone() {
        phoneButtonIsVisible = false

        val contact = Contact(phone = "8675309")
        presenter.venueItem = VenueItem(venue = Venue(contact = contact))

        verify(mockedView).showPhoneButton()

        assertTrue("Phone button is visible when there is a number.", phoneButtonIsVisible)
    }

    @Test
    fun hidesPhoneIfNoPhone() {
        phoneButtonIsVisible = true

        presenter.venueItem = VenueItem(venue = Venue())

        verify(mockedView).hidePhoneButton()

        assertFalse("Phone button is not visible when there is no number.", phoneButtonIsVisible)
    }

    @Test
    fun showsWebsiteIfHasWebsite() {
        websiteButtonIsVisible = false

        presenter.venueItem = VenueItem(venue = Venue(url = "food.com"))

        verify(mockedView).showWebsiteButton()

        assertTrue("Website button is visible when there is a website.", websiteButtonIsVisible)
    }

    @Test
    fun hidesWebsiteIfNoWebsite() {
        websiteButtonIsVisible = true

        presenter.venueItem = VenueItem(venue = Venue())

        verify(mockedView).hideWebsiteButton()

        assertFalse("Website button is not visible when there is no website.", websiteButtonIsVisible)
    }

    @Test
    fun launchesMenuWhenButtonClicked() {
        val menu = Menu(url = "menu.com")
        presenter.venueItem = VenueItem(venue = Venue(menu = menu))
        presenter.onMenuButtonClicked()

        verify(mockedView).launchUrl("menu.com")
    }

    @Test
    fun showsErrorWhenMenuButtonClickedWithNoMenu() {
        presenter.venueItem = VenueItem(venue = Venue())
        presenter.onMenuButtonClicked()

        verify(mockedView).showMenuNotAvailableError()
    }

    @Test
    fun launchesWebsiteWhenButtonClicked() {
        presenter.venueItem = VenueItem(venue = Venue(url = "food.com"))
        presenter.onWebsiteButtonClicked()

        verify(mockedView).launchUrl("food.com")
    }

    @Test
    fun showsErrorWhenWebsiteButtonClickedWithNoWebsite() {
        presenter.venueItem = VenueItem(venue = Venue())
        presenter.onWebsiteButtonClicked()

        verify(mockedView).showWebsiteNotAvailableError()
    }

    @Test
    fun dialsPhoneWhenPhoneButtonPressed() {
        val contact = Contact(phone = "8675309")
        presenter.venueItem = VenueItem(venue = Venue(contact = contact))
        presenter.onPhoneButtonClicked()

        verify(mockedView).launchDialer("8675309")
    }

    @Test
    fun showsErrorWhenPhoneButtonClickedWithNoPhone() {
        presenter.venueItem = VenueItem(venue = Venue())
        presenter.onPhoneButtonClicked()

        verify(mockedView).showPhoneNumberNotAvailableError()
    }
}