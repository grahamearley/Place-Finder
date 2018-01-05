package website.grahamearley.placefinder.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_place_list.*
import website.grahamearley.placefinder.R
import website.grahamearley.placefinder.hide
import website.grahamearley.placefinder.show


class PlaceListActivity : PlaceListViewContract, AppCompatActivity() {

    override val presenter: PlaceListPresenterContract = PlaceListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_list)

        searchButton.setOnClickListener { onSearchButtonClick() }
    }

    override fun showStatusText() {
        statusTextView.show()
    }

    override fun hideStatusText() {
        statusTextView.hide()
    }

    override fun setStatusText(text: String) {
        statusTextView.text = text
    }

    override fun onSearchButtonClick() {
        val query = getQueryText()
        val location = getLocationText()

        presenter.onNewVenueQuery(query, location)
    }

    private fun getQueryText(): String = queryEditText.text.toString()

    private fun getLocationText(): String = locationEditText.text.toString()
}

