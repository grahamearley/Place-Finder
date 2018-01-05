package website.grahamearley.placefinder.list

import website.grahamearley.placefinder.data.FoursquareInteractor
import website.grahamearley.placefinder.data.FoursquareInteractorContract
import website.grahamearley.placefinder.enqueue

class PlaceListPresenter(override val view: PlaceListViewContract) : PlaceListPresenterContract {

    override val interactor: FoursquareInteractorContract = FoursquareInteractor()

    override fun onNewVenueQuery(query: String, near: String) {
        val call = interactor.getPlacesCall(query, near)
        call.enqueue(onResponse = { response ->
            val venueNames = response?.body()?.response?.groups
                    ?.flatMap { it.items }
                    ?.map { it.venue.name }

            updateVenuesList(venueNames)
        })
    }

    private fun updateVenuesList(names: List<String>?) {
        view.showStatusText()
        view.setStatusText(names?.joinToString(separator = ",").orEmpty())
    }
}