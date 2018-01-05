package website.grahamearley.placefinder.list.contract

import android.support.annotation.StringRes

/**
 * Created by grahamearley on 1/5/18.
 */
interface PlaceListViewContract {
    val presenter: PlaceListPresenterContract

    fun showStatusText()

    fun hideStatusText()

    fun setStatusText(@StringRes stringRes: Int)

    fun setStatusText(text: String)

    fun onSearchButtonClick()
}