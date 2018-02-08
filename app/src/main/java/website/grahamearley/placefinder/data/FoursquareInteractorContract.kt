package website.grahamearley.placefinder.data

import io.reactivex.Single
import website.grahamearley.placefinder.model.Tip
import website.grahamearley.placefinder.model.VenueItem

/**
 * Contract for interactors with the Foursquare API.
 */
interface FoursquareInteractorContract {
    fun requestPlaces(query: String, near: String): Single<List<VenueItem>>

    fun requestVenueTips(venueId: String): Single<List<Tip>>

    fun requestVenuePhotos(venueId: String): Single<List<String>>
}