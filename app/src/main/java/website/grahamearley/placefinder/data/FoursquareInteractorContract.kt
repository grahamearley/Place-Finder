package website.grahamearley.placefinder.data

import retrofit2.Response
import website.grahamearley.placefinder.FoursquareResponse

/**
 * Created by grahamearley on 1/5/18.
 */
interface FoursquareInteractorContract {
    fun getPlacesAsync(query: String, near: String,
                       onResponse: (response: Response<FoursquareResponse>?) -> Unit,
                       onFailure: (throwable: Throwable?) -> Unit)

    fun getVenueTipsAsync(venueId: String,
                          onResponse: (response: Response<FoursquareResponse>?) -> Unit,
                          onFailure: (throwable: Throwable?) -> Unit)
}