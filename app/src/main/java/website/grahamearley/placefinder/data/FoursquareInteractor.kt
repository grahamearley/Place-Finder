package website.grahamearley.placefinder.data

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import website.grahamearley.placefinder.API_BASE_URL
import website.grahamearley.placefinder.Tip
import website.grahamearley.placefinder.VenueItem

/**
 *  Makes calls to the Foursquare API, implementing the
 *    Foursquare Interactor contract.
 */
class FoursquareInteractor : FoursquareInteractorContract {

    private val foursquareRetrofit by lazy {
        Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(FoursquareApi::class.java)
    }

    /**
     * Request a list of places near a location for a given query.
     */
    override fun requestPlaces(query: String, near: String): Single<List<VenueItem>> {
        val placesSingle = foursquareRetrofit.requestVenues(near = near,
                query = query, venuePhotos = 1) // 1 => include photos

        return placesSingle.map {
            it.response.groups?.flatMap { it.items.orEmpty() }
        }
    }

    /**
     * Request a list of tips for a venue.
     */
    override fun requestVenueTips(venueId: String): Single<List<Tip>> {
        val tipsSingle = foursquareRetrofit.requestVenueTips(venueId = venueId)

        return tipsSingle.map {
            it.response.tips?.items
        }
    }

    /**
     * Request a list of photos for a venue.
     */
    override fun requestVenuePhotos(venueId: String): Single<List<String>> {
        val photosSingle = foursquareRetrofit.requestVenuePhotos(venueId = venueId)

        return photosSingle.map {
            it.response.photos?.items?.map { it.getUrl() }
        }
    }

}