package website.grahamearley.placefinder.list

import website.grahamearley.placefinder.R
import website.grahamearley.placefinder.data.FoursquareInteractor
import website.grahamearley.placefinder.data.FoursquareInteractorContract
import website.grahamearley.placefinder.enqueue
import website.grahamearley.placefinder.list.contract.PlaceListPresenterContract
import website.grahamearley.placefinder.list.contract.PlaceListViewContract

class PlaceListPresenter(override val view: PlaceListViewContract) : PlaceListPresenterContract {

    override val interactor: FoursquareInteractorContract = FoursquareInteractor()

    override fun onNewVenueQuery(query: String, near: String) {
        val call = interactor.getPlacesCall(query, near)
        call.enqueue(onResponse = { response ->
            val venueNames = response?.body()?.response?.groups
                    ?.flatMap { it.items }
                    ?.map { it.venue.name }

            updateVenuesList(venueNames)
        }, onFailure = { _ ->
            showErrorStatus()
        })
    }

    private fun updateVenuesList(names: List<String>?) {
        view.setStatusText(names?.joinToString(separator = ",").orEmpty())
        view.showStatusText()
    }

    private fun showErrorStatus() {
        view.setStatusText(R.string.could_not_load_places_error)
        view.showStatusText()
    }
}