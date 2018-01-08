package website.grahamearley.placefinder.detail.contract

/**
 * Interface for the Place Detail presenter.
 */
interface PlaceDetailPresenterContract {
    val view: PlaceDetailViewContract

    fun initializeView()
}