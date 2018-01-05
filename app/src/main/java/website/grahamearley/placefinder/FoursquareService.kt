package website.grahamearley.placefinder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A service for requesting venues from the Foursquare API.
 */
interface FoursquareService {
    @GET("venues/explore")
    fun requestVenues(@Query("client_id") clientId: String = CLIENT_ID,
                      @Query("client_secret") clientSecret: String = CLIENT_SECRET,
                      @Query("v") version: String = API_VERSION,
                      @Query("section") section: String = "food",
                      @Query("near") near: String,
                      @Query("query") query: String,
                      @Query("venuePhotos") venuePhotos: Int): Call<FoursquareResponse>
}