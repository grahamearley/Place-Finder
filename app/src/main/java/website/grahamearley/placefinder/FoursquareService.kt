package website.grahamearley.placefinder

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by grahamearley on 1/4/18.
 */
interface FoursquareService {
    @GET("venues/explore")
    fun requestVenues(@Query("client_id") clientId: String,
                      @Query("client_secret") clientSecret: String,
                      @Query("section") section: String = "food",
                      @Query("near") near: String,
                      @Query("query") query: String,
                      @Query("venuePhotos") venuePhotos: Int)
}