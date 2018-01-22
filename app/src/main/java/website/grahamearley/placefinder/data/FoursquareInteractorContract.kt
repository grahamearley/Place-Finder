package website.grahamearley.placefinder.data

import io.reactivex.Single
import website.grahamearley.placefinder.FoursquareResponse

/**
 * Contract for interactors with the Foursquare API.
 */
interface FoursquareInteractorContract {
    fun requestPlaces(query: String, near: String): Single<FoursquareResponse>

    fun requestVenueTips(venueId: String): Single<FoursquareResponse>

    fun requestVenuePhotos(venueId: String): Single<FoursquareResponse>
}