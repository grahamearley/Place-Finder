package website.grahamearley.placefinder.ui.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_place_list.*
import kotlinx.android.synthetic.main.search_bar.*
import website.grahamearley.placefinder.*
import website.grahamearley.placefinder.ui.detail.PlaceDetailActivity
import website.grahamearley.placefinder.ui.list.contract.PlaceListPresenterContract
import website.grahamearley.placefinder.ui.list.contract.PlaceListViewContract

/**
 * The PlaceListActivity allows the user to search for places
 *  using a query term and a location term. A search will return
 *  a list of [Venue]s, provided the search has results.
 */
class PlaceListActivity : PlaceListViewContract, AppCompatActivity() {

    companion object {
        const val QUERY_TEXT = "QUERY_TEXT"
        const val LOCATION_TEXT = "LOCATION_TEXT"
    }

    override val presenter: PlaceListPresenterContract = PlaceListPresenter(this)
    private val adapter = VenueRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_list)
        initializeUi()
    }

    private fun initializeUi() {
        searchBar.setElevation(R.dimen.search_elevation)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = this.adapter

        searchButton.setOnClickListener { onSearchButtonClick() }

        val keyboardSearchButtonListener = TextView.OnEditorActionListener { _, _, _ ->
            onSearchButtonClick()
            true
        }

        queryEditText.setOnEditorActionListener(keyboardSearchButtonListener)
        locationEditText.setOnEditorActionListener(keyboardSearchButtonListener)

        if (adapter.isEmpty()) {
            setSearchBarGravityToCenter()
        } else {
            setSearchBarGravityToBottom()
        }

        setInitialSearchTerms()
    }

    private fun setInitialSearchTerms() {
        queryEditText.setText(R.string.tacos)
        locationEditText.setText(R.string.minneapolis)
    }

    override fun showListItems() {
        recyclerView.show()
    }

    override fun hideListItems() {
        recyclerView.hide()
    }

    override fun setListItems(venues: List<VenueItem>) {
        adapter.setVenues(venues)
        recyclerView.scrollToPosition(0)
        adapter.setOnVenueClickListener { venueItem ->
            presenter.onVenueItemClicked(venueItem)
        }
    }

    override fun launchVenueDetailView(venueItem: VenueItem) {
        PlaceDetailActivity.launch(this, venueItem)
    }

    override fun showStatusText() {
        statusTextView.show()
    }

    override fun hideStatusText() {
        statusTextView.hide()
    }

    override fun setStatusText(stringRes: Int) {
        val text = getString(stringRes)
        setStatusText(text)
    }

    override fun setStatusText(text: String) {
        statusTextView.text = text
    }

    override fun onSearchButtonClick() {
        val query = getQueryText()
        val location = getLocationText()

        hideSoftKeyboard()
        presenter.onNewVenueQuery(query, location)
    }

    override fun showProgressBar() {
        progressBar.show()
    }

    override fun hideProgressBar() {
        progressBar.hide()
    }

    override fun setSearchBarGravityToCenter() {
        setSearchBarGravity(Gravity.CENTER)
    }

    override fun setSearchBarGravityToBottom() {
        setSearchBarGravity(Gravity.BOTTOM)
    }

    private fun setSearchBarGravity(gravity: Int) {
        val layoutParams = searchBar.layoutParams as FrameLayout.LayoutParams
        layoutParams.gravity = gravity
        searchBar.layoutParams = layoutParams
    }

    private fun getQueryText(): String = queryEditText.text.toString()

    private fun getLocationText(): String = locationEditText.text.toString()

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(QUERY_TEXT, queryEditText.text.toString())
        outState?.putString(LOCATION_TEXT, locationEditText.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        val queryText = savedInstanceState?.getString(QUERY_TEXT)
        val locationText = savedInstanceState?.getString(LOCATION_TEXT)

        queryEditText.setText(queryText)
        locationEditText.setText(locationText)

        super.onRestoreInstanceState(savedInstanceState)
    }
}

