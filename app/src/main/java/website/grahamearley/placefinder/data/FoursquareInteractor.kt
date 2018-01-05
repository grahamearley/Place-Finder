package website.grahamearley.placefinder.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import website.grahamearley.placefinder.API_BASE_URL
import website.grahamearley.placefinder.FoursquareResponse

/**
 * Created by grahamearley on 1/5/18.
 */
class FoursquareInteractor : FoursquareInteractorContract {
    override fun getPlacesCall(query: String, near: String): Call<FoursquareResponse> {
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(FoursquareApi::class.java)
                .requestVenues(section = "food",
                        near = near,
                        query = query,
                        venuePhotos = 1) // 1 => get photos
    }
}