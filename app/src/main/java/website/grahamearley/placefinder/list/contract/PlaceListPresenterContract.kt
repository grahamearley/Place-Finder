package website.grahamearley.placefinder.list.contract

import website.grahamearley.placefinder.data.FoursquareInteractorContract

/**
 * Created by grahamearley on 1/5/18.
 */
interface PlaceListPresenterContract {
    val view: PlaceListViewContract
    val interactor: FoursquareInteractorContract

    fun onNewVenueQuery(query: String, near: String)
}