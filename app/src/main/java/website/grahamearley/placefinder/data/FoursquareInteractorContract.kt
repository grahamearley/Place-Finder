package website.grahamearley.placefinder.data

import retrofit2.Call
import website.grahamearley.placefinder.FoursquareResponse

/**
 * Created by grahamearley on 1/5/18.
 */
interface FoursquareInteractorContract {
    fun getPlacesCall(query: String, near: String): Call<FoursquareResponse>
}