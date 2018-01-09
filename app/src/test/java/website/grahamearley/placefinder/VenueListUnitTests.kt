package website.grahamearley.placefinder

import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import website.grahamearley.placefinder.ui.list.PlaceListPresenter
import website.grahamearley.placefinder.ui.list.contract.PlaceListViewContract
import org.mockito.Mockito.*
import website.grahamearley.placefinder.data.FoursquareInteractorContract

/**
 * Unit tests for the venue list activity, testing the contract
 *  between presenter, interactor, and view.
 *
 *  The mocked view has stubbed methods that change boolean flags
 *   indicating view visibility, for verifying whether these views
 *   are visible or not. These view visibilities are reset before
 *   each test to be the opposite of what the tested method should
 *   produce.
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class VenueListUnitTests {

    private val mockedView = mock(PlaceListViewContract::class.java)
    private val presenter = PlaceListPresenter(mockedView)

    private val dummyVenue = Venue(name = "Maya Cuisine")
    private val dummyVenueItem = VenueItem(venue = dummyVenue)
    private val dummyVenueGroup = VenueGroup(items = listOf(dummyVenueItem))
    private val dummyVenueGroupEmpty = VenueGroup(items = emptyList())
    private val dummyResponse = Response(groups = listOf(dummyVenueGroup))
    private val dummyResponseEmpty = Response(groups = listOf(dummyVenueGroupEmpty))

    private var progressBarIsVisible = false
    private var listItemsAreVisible = true
    private var statusTextIsVisible = false

    @Before
    fun stubViewMethods() {
        Mockito.`when`(mockedView.hideListItems()).then {
            listItemsAreVisible = false
            null
        }

        Mockito.`when`(mockedView.showListItems()).then {
            listItemsAreVisible = true
            null
        }

        Mockito.`when`(mockedView.showStatusText()).then {
            statusTextIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideStatusText()).then {
            statusTextIsVisible = false
            null
        }

        Mockito.`when`(mockedView.showProgressBar()).then {
            progressBarIsVisible = true
            null
        }

        Mockito.`when`(mockedView.hideProgressBar()).then {
            progressBarIsVisible = false
            null
        }
    }

    @Test
    fun onNewVenueQuery_showsErrorForEmptyNearField() {
        statusTextIsVisible = false
        progressBarIsVisible = true
        listItemsAreVisible = true

        presenter.onNewVenueQuery(query = "tacos", near = "")

        verify(mockedView).setStatusText(R.string.you_need_to_specify_a_location_for_your_search)
        verify(mockedView, never())
                .setStatusText(Mockito.intThat {
                    it != R.string.you_need_to_specify_a_location_for_your_search
                })

        assertTrue("Status text is visible when no near field is specified.", statusTextIsVisible)
        assertFalse("Item list is not visible when no near field is specified.", listItemsAreVisible)
        assertFalse("Progress bar is not visible when no near field is specified.", progressBarIsVisible)
    }

    @Test
    fun onNewVenueQuery_showsProgressBarForQueryWithLocation() {
        progressBarIsVisible = false
        statusTextIsVisible = true
        listItemsAreVisible = true

        presenter.onNewVenueQuery(query = "tacos", near = "minneapolis")
        assertTrue("Progress bar is visible when search is performed (with location included).", progressBarIsVisible)
        assertFalse("Status text is not visible when search is performed (with location included).", statusTextIsVisible)
        assertFalse("Item list is not visible when search is performed (with location included).", listItemsAreVisible)
    }

    @Test
    fun onNewVenueQuery_showsErrorMessageOnFailure() {
        statusTextIsVisible = false
        progressBarIsVisible = true
        listItemsAreVisible = true

        val errorInteractor = object: FoursquareInteractorContract {
            override fun getVenueTipsAsync(venueId: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getPlacesAsync(query: String, near: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                onFailure(null)
            }
        }

        val errorPresenter = PlaceListPresenter(mockedView, errorInteractor)
        errorPresenter.onNewVenueQuery(query = "thali", near = "pune")

        verify(mockedView).setStatusText(R.string.could_not_load_places_error)
        verify(mockedView).showStatusText()

        assertTrue("Status text is visible when request fails.", statusTextIsVisible)
        assertFalse("Progress bar is not visible when request fails.", progressBarIsVisible)
        assertFalse("Item list is not visible when request fails.", listItemsAreVisible)
    }

    @Test
    fun onNewVenueQuery_showsItemsOnResponse() {
        listItemsAreVisible = false
        statusTextIsVisible = true
        progressBarIsVisible = true

        val itemsInteractor = object: FoursquareInteractorContract {
            override fun getVenueTipsAsync(venueId: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getPlacesAsync(query: String, near: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                val foursquareResponse = FoursquareResponse(response = dummyResponse)
                val retrofitResponse = retrofit2.Response.success(foursquareResponse)

                onResponse(retrofitResponse)
            }
        }

        val itemPresenter = PlaceListPresenter(mockedView, itemsInteractor)
        itemPresenter.onNewVenueQuery(query = "burritos", near = "minneapolis")

        verify(mockedView).setListItems(Mockito.anyList())
        verify(mockedView).showListItems()

        assertTrue("Item list is visible when response is successful.", listItemsAreVisible)
        assertFalse("Status text is not visible when response is successful.", statusTextIsVisible)
        assertFalse("Progress bar is not visible when response is successful.", progressBarIsVisible)
    }

    @Test
    fun onNewVenueQuery_showEmptyStatusTextForEmptyResponse() {
        statusTextIsVisible = false
        listItemsAreVisible = true
        progressBarIsVisible = true

        val emptyInteractor = object: FoursquareInteractorContract {
            override fun getVenueTipsAsync(venueId: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}
            override fun getVenuePhotosAsync(venueId: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {}

            override fun getPlacesAsync(query: String, near: String, onResponse: (response: retrofit2.Response<FoursquareResponse>?) -> Unit, onFailure: (throwable: Throwable?) -> Unit) {
                val foursquareResponse = FoursquareResponse(response = dummyResponseEmpty)
                val retrofitResponse = retrofit2.Response.success(foursquareResponse)

                onResponse(retrofitResponse)
            }
        }

        val emptyListPresenter = PlaceListPresenter(mockedView, emptyInteractor)
        emptyListPresenter.onNewVenueQuery(query = "coffee", near = "san francisco")

        verify(mockedView).setStatusText(R.string.no_places_found)
        verify(mockedView).showStatusText()

        assertTrue("Status text not visible when response is empty.", statusTextIsVisible)
        assertFalse("Item list is not visible when response is empty.", listItemsAreVisible)
        assertFalse("Progress bar is not visible when response is empty.", progressBarIsVisible)
    }
}