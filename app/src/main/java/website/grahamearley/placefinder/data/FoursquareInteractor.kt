package website.grahamearley.placefinder.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import website.grahamearley.placefinder.API_BASE_URL
import website.grahamearley.placefinder.FoursquareResponse
import website.grahamearley.placefinder.enqueue

/**
 *  Makes calls to the Foursquare API, implementing the
 *    Foursquare Interactor contract.
 */
class FoursquareInteractor : FoursquareInteractorContract {

    override fun getPlacesAsync(query: String, near: String,
                                onResponse: (response: Response<FoursquareResponse>?) -> Unit,
                                onFailure: (throwable: Throwable?) -> Unit) {

        val call = getFoursquareRetrofitApi().requestVenues(near = near,
                query = query, venuePhotos = 1) // 1 => include photos

        call.enqueue(onResponse, onFailure)
    }

    override fun getVenueTipsAsync(venueId: String,
                                   onResponse: (response: Response<FoursquareResponse>?) -> Unit,
                                   onFailure: (throwable: Throwable?) -> Unit) {

        val call = getFoursquareRetrofitApi()
                .requestTips(venueId = venueId)

        call.enqueue(onResponse, onFailure)
    }

    private fun getFoursquareRetrofitApi(): FoursquareApi {
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(FoursquareApi::class.java)
    }
}