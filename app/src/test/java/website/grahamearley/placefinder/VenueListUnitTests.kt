package website.grahamearley.placefinder

import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import website.grahamearley.placefinder.model.Venue
import website.grahamearley.placefinder.model.VenueItem
import website.grahamearley.placefinder.ui.list.PlaceListPresenter
import website.grahamearley.placefinder.ui.list.contract.PlaceListViewContract

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
    private val presenter = PlaceListPresenter(mockedView, observationScheduler = TestScheduler())

    private val dummyVenue = Venue(name = "Maya Cuisine")
    private val dummyVenueItem = VenueItem(venue = dummyVenue)

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
    fun showsErrorForEmptyNearField() {
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
    fun showsProgressBarForQueryWithLocation() {
        progressBarIsVisible = false
        statusTextIsVisible = true
        listItemsAreVisible = true

        presenter.onNewVenueQuery(query = "tacos", near = "minneapolis")
        assertTrue("Progress bar is visible when search is performed (with location included).", progressBarIsVisible)
        assertFalse("Status text is not visible when search is performed (with location included).", statusTextIsVisible)
        assertFalse("Item list is not visible when search is performed (with location included).", listItemsAreVisible)
    }

    @Test
    fun showsErrorMessageOnVenueRequestError() {
        statusTextIsVisible = false
        progressBarIsVisible = true
        listItemsAreVisible = true

        presenter.onVenuesRequestError(Exception("Error getting places."))

        verify(mockedView).setStatusText(R.string.could_not_load_places_error)
        verify(mockedView).showStatusText()

        assertTrue("Status text is visible when request fails.", statusTextIsVisible)
        assertFalse("Progress bar is not visible when request fails.", progressBarIsVisible)
        assertFalse("Item list is not visible when request fails.", listItemsAreVisible)
    }

    @Test
    fun showsItemsOnNonEmptyResponse() {
        listItemsAreVisible = false
        statusTextIsVisible = true
        progressBarIsVisible = true

        presenter.onVenuesLoaded(listOf(dummyVenueItem))

        verify(mockedView).setListItems(Mockito.anyList())
        verify(mockedView).showListItems()

        assertTrue("Item list is visible when response is successful.", listItemsAreVisible)
        assertFalse("Status text is not visible when response is successful.", statusTextIsVisible)
        assertFalse("Progress bar is not visible when response is successful.", progressBarIsVisible)
    }

    @Test
    fun showsEmptyStatusTextForEmptyResponse() {
        statusTextIsVisible = false
        listItemsAreVisible = true
        progressBarIsVisible = true

        presenter.onVenuesLoaded(emptyList())

        verify(mockedView).setStatusText(R.string.no_places_found)
        verify(mockedView).showStatusText()

        assertTrue("Status text not visible when response is empty.", statusTextIsVisible)
        assertFalse("Item list is not visible when response is empty.", listItemsAreVisible)
        assertFalse("Progress bar is not visible when response is empty.", progressBarIsVisible)
    }
}