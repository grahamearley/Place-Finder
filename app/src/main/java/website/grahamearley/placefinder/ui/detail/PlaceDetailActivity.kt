package website.grahamearley.placefinder.ui.detail

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_place_detail.*
import website.grahamearley.placefinder.*
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailPresenterContract
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailViewContract

class PlaceDetailActivity : PlaceDetailViewContract, AppCompatActivity() {
    companion object {
        fun launch(fromActivity: Activity, venueItem: VenueItem) {
            val intent = Intent(fromActivity, PlaceDetailActivity::class.java)
            intent.putExtra(EXTRA_VENUE_ITEM, venueItem)

            fromActivity.startActivity(intent)
        }

        private const val EXTRA_VENUE_ITEM = "EXTRA_VENUE_ITEM"
        fun Intent.getVenueItem(): VenueItem? {
            return if (hasExtra(EXTRA_VENUE_ITEM)) {
                getParcelableExtra(EXTRA_VENUE_ITEM)
            } else {
                null
            }
        }
    }

    override val presenter: PlaceDetailPresenterContract = PlaceDetailPresenter(this)

    private val venueItem by lazy { intent.getVenueItem() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        if (venueItem == null) {
            // If venueItem was not passed in as an extra, finish with error.
            showToast(R.string.there_was_an_error_loading_details_for_that_place)
            finish()
        } else {
            presenter.venueItem = venueItem
        }

        presenter.onViewCreated()
    }

    override fun setReason(reason: String) {
        reasonTextView.text = reason
    }

    override fun showReason() = reasonTextView.show()

    override fun hideReason() = reasonTextView.hide()

    override fun setVenueImages(urls: List<String>) {
        // TODO
    }

    override fun showVenueImages() = photoRecyclerView.show()

    override fun hideVenueImages() = photoRecyclerView.hide()

    override fun setVenueTips(tips: List<Tip>) {
        // TODO
    }

    override fun showVenueTips() {
        // TODO
    }

    override fun hideVenueTips() {
        // TODO
    }

    override fun setVenueName(name: String) {
        nameTextView.text = name
    }

    override fun showVenueName() = nameTextView.show()

    override fun hideVenueName() = nameTextView.hide()

    override fun setVenueAddress(address: String) {
        addressTextView.text = address
    }

    override fun showVenueAddress() = addressTextView.show()

    override fun hideVenueAddress() = addressTextView.hide()

    override fun setVenueCategory(category: String) {
        categoryTextView.text = category
    }

    override fun showVenueCategory() = categoryTextView.show()

    override fun hideVenueCategory() = categoryTextView.hide()

    override fun setRating(rating: Double) {
        ratingTextView.text = rating.toString()
    }

    override fun setRatingColor(color: String?) {
        color?.let {
            val colorString = "#$color"
            ratingTextView.setBackgroundColor(Color.parseColor(colorString))
        }
    }

    override fun showRating() = ratingTextView.show()

    override fun hideRating() = ratingTextView.hide()

    override fun setMenuUrl(url: String) {
        // TODO
    }

    override fun showMenuButton() {
        // TODO
    }

    override fun hideMenuButton() {
        // TODO
    }

    override fun setPhoneNumber(phoneNumber: String) {
        // TODO
    }

    override fun showPhoneButton() {
        // TODO
    }

    override fun hidePhoneButton() {
        // TODO
    }

    override fun setWebsite(url: String) {
        // TODO
    }

    override fun showWebsiteButton() {
        // TODO
    }

    override fun hideWebsiteButton() {
        // TODO
    }

    override fun setOnClickListeners() {
        // TODO
    }

    override fun launchUrl(url: String) {
        // TODO
    }

    override fun launchDialer(phoneNumber: String) {
        // TODO
    }

    override fun showMenuNotAvailableError() {
        // TODO
    }

    override fun showPhoneNumberNotAvailableError() {
        // TODO
    }

    override fun showWebsiteNotAvailableError() {
        // TODO
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                // Mimic back button when "up" button is pressed:
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
