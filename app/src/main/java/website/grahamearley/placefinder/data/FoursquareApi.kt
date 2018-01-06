package website.grahamearley.placefinder.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import website.grahamearley.placefinder.API_VERSION
import website.grahamearley.placefinder.CLIENT_ID
import website.grahamearley.placefinder.CLIENT_SECRET
import website.grahamearley.placefinder.FoursquareResponse

/**
 * An interface for requesting venues from the Foursquare API.
 */
interface FoursquareApi {
    @GET("venues/explore")
    fun requestVenues(@Query("client_id") clientId: String = CLIENT_ID,
                      @Query("client_secret") clientSecret: String = CLIENT_SECRET,
                      @Query("v") version: String = API_VERSION,
                      @Query("near") near: String,
                      @Query("query") query: String,
                      @Query("venuePhotos") venuePhotos: Int): Call<FoursquareResponse>
}