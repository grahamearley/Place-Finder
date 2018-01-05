package website.grahamearley.placefinder.list

/**
 * Created by grahamearley on 1/5/18.
 */
interface PlaceListViewContract {
    val presenter: PlaceListPresenterContract

    fun showStatusText()

    fun hideStatusText()

    fun setStatusText(text: String)

    fun onSearchButtonClick()
}