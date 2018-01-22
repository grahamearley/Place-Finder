package website.grahamearley.placefinder.data

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import website.grahamearley.placefinder.API_BASE_URL
import website.grahamearley.placefinder.FoursquareResponse

/**
 *  Makes calls to the Foursquare API, implementing the
 *    Foursquare Interactor contract.
 */
class FoursquareInteractor : FoursquareInteractorContract {

    override fun requestPlaces(query: String, near: String): Single<FoursquareResponse> {
        return getFoursquareRetrofitApi().requestVenues(near = near,
                query = query, venuePhotos = 1) // 1 => include photos
    }

    override fun requestVenueTips(venueId: String): Single<FoursquareResponse> {
        return getFoursquareRetrofitApi()
                .requestVenueTips(venueId = venueId)
    }

    override fun requestVenuePhotos(venueId: String): Single<FoursquareResponse> {
        return getFoursquareRetrofitApi()
                .requestVenuePhotos(venueId = venueId)
    }

    private fun getFoursquareRetrofitApi(): FoursquareApi {
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(FoursquareApi::class.java)
    }
}