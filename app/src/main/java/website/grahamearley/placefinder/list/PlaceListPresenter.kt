package website.grahamearley.placefinder.list

import website.grahamearley.placefinder.R
import website.grahamearley.placefinder.VenueItem
import website.grahamearley.placefinder.data.FoursquareInteractor
import website.grahamearley.placefinder.data.FoursquareInteractorContract
import website.grahamearley.placefinder.enqueue
import website.grahamearley.placefinder.list.contract.PlaceListPresenterContract
import website.grahamearley.placefinder.list.contract.PlaceListViewContract

class PlaceListPresenter(override val view: PlaceListViewContract) : PlaceListPresenterContract {

    override val interactor: FoursquareInteractorContract = FoursquareInteractor()

    override fun onNewVenueQuery(query: String, near: String) {
        view.hideStatusText()
        view.showProgressBar()

        val call = interactor.getPlacesCall(query, near)
        call.enqueue(onResponse = { response ->
            val venues = response?.body()?.response?.groups
                    ?.flatMap { it.items }

            updateVenuesList(venues)
        }, onFailure = { _ ->
            showErrorStatus()
        })
    }

    private fun updateVenuesList(venues: List<VenueItem>?) {
        view.hideProgressBar()

        if (venues == null || venues.isEmpty()) {
            view.setStatusText(R.string.no_places_found)
        } else {
            view.setListItems(venues)
        }
    }

    private fun showErrorStatus() {
        view.hideProgressBar()

        view.setStatusText(R.string.could_not_load_places_error)
        view.showStatusText()
    }
}