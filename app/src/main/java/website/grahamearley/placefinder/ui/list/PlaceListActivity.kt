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
import website.grahamearley.placefinder.ui.list.contract.PlaceListPresenterContract
import website.grahamearley.placefinder.ui.list.contract.PlaceListViewContract


class PlaceListActivity : PlaceListViewContract, AppCompatActivity() {

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
    }

    override fun showListItems() {
        recyclerView.show()
    }

    override fun hideListItems() {
        recyclerView.hide()
    }

    override fun setListItems(venues: List<VenueItem>) {
        adapter.setVenues(venues)
        adapter.setOnVenueClickListener { venueItem ->
            presenter.onVenueItemClicked(venueItem)
        }
    }

    override fun launchVenueDetailView(venueItem: VenueItem) {
        // TODO
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
}

