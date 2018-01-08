package website.grahamearley.placefinder.ui.list

import website.grahamearley.placefinder.R
import website.grahamearley.placefinder.VenueItem
import website.grahamearley.placefinder.data.FoursquareInteractor
import website.grahamearley.placefinder.data.FoursquareInteractorContract
import website.grahamearley.placefinder.ui.list.contract.PlaceListPresenterContract
import website.grahamearley.placefinder.ui.list.contract.PlaceListViewContract

class PlaceListPresenter(override val view: PlaceListViewContract,
                         override val interactor: FoursquareInteractorContract
                            = FoursquareInteractor()) : PlaceListPresenterContract {

    override fun onNewVenueQuery(query: String, near: String) {
        view.setSearchBarGravityToBottom()

        view.hideStatusText()
        view.hideListItems()
        
        if (near.isEmpty()) {
            view.hideProgressBar()
            view.setStatusText(R.string.you_need_to_specify_a_location_for_your_search)
            view.showStatusText()
        } else {
            view.showProgressBar()

            interactor.getPlacesAsync(query, near,
                    onResponse = { response ->
                        val venues = response?.body()?.response?.groups
                                            ?.flatMap { it.items }
                        updateVenuesList(venues)
                    }, onFailure = { _ ->
                        showErrorStatus()
                    })
        }
    }

    private fun updateVenuesList(venues: List<VenueItem>?) {
        view.hideListItems()
        view.hideProgressBar()

        if (venues == null || venues.isEmpty()) {
            view.setStatusText(R.string.no_places_found)
            view.showStatusText()
        } else {
            view.setListItems(venues)
            view.showListItems()
        }
    }

    private fun showErrorStatus() {
        view.hideProgressBar()
        view.hideListItems()

        view.setStatusText(R.string.could_not_load_places_error)
        view.showStatusText()
    }
}