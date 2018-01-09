package website.grahamearley.placefinder.data

import retrofit2.Response
import website.grahamearley.placefinder.FoursquareResponse

/**
 * Contract for interactors with the Foursquare API.
 */
interface FoursquareInteractorContract {
    fun getPlacesAsync(query: String, near: String,
                       onResponse: (response: Response<FoursquareResponse>?) -> Unit,
                       onFailure: (throwable: Throwable?) -> Unit)

    fun getVenueTipsAsync(venueId: String,
                          onResponse: (response: Response<FoursquareResponse>?) -> Unit,
                          onFailure: (throwable: Throwable?) -> Unit)

    fun getVenuePhotosAsync(venueId: String,
                          onResponse: (response: Response<FoursquareResponse>?) -> Unit,
                          onFailure: (throwable: Throwable?) -> Unit)
}