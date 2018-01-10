package website.grahamearley.placefinder.ui.detail

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_place_detail.*
import website.grahamearley.placefinder.*
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailPresenterContract
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailViewContract

/**
 * The PlaceDetailActivity shows details about a place,
 *  including images and tips. It allows the user to
 *  call the place, see the place's menu, and/or visit
 *  the place's website.
 */
class PlaceDetailActivity : PlaceDetailViewContract, AppCompatActivity() {
    companion object {
        fun launch(fromActivity: Activity, venueItem: VenueItem) {
            val intent = Intent(fromActivity, PlaceDetailActivity::class.java)
            intent.putExtra(EXTRA_VENUE_ITEM, venueItem)

            fromActivity.startActivityWithTransitionIfPossible(intent)
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
    private val imageAdapter = ImageRecyclerAdapter()
    private val tipAdapter = TipRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        if (venueItem == null) {
            // If venueItem was not passed in as an extra, finish with error.
            showToast(R.string.there_was_an_error_loading_details_for_that_place)
            finish()
        } else {
            initializeUi()
            presenter.venueItem = venueItem
        }
    }

    private fun initializeUi() {
        photoRecyclerView.setHasFixedSize(true)
        photoRecyclerView.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
        photoRecyclerView.adapter = imageAdapter

        tipsRecyclerView.layoutManager = LinearLayoutManager(this)
        tipsRecyclerView.adapter = tipAdapter
    }

    override fun setReason(reason: String) {
        reasonTextView.text = reason
    }

    override fun showReason() = reasonTextView.show()

    override fun hideReason() = reasonTextView.hide()

    override fun setVenueImages(urls: List<String>) {
        imageAdapter.setImageUrls(urls)
    }

    override fun showVenueImages() = photoRecyclerView.show()

    override fun hideVenueImages() = photoRecyclerView.hide()

    override fun setVenueTips(tips: List<Tip>) {
        tipAdapter.setTips(tips)
    }

    override fun showVenueTips() {
        tipsRecyclerView.show()
    }

    override fun hideVenueTips() {
        tipsRecyclerView.hide()
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

    override fun showMenuButton() = menuButton.show()

    override fun hideMenuButton() = menuButton.hide()

    override fun showPhoneButton() = phoneButton.show()

    override fun hidePhoneButton() = phoneButton.hide()

    override fun showWebsiteButton() = websiteButton.show()

    override fun hideWebsiteButton() = websiteButton.hide()

    override fun setOnClickListeners() {
        menuButton.setOnClickListener { presenter.onMenuButtonClicked() }
        phoneButton.setOnClickListener { presenter.onPhoneButtonClicked() }
        websiteButton.setOnClickListener { presenter.onWebsiteButtonClicked() }
    }

    override fun launchUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun launchDialer(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    override fun showMenuNotAvailableError() {
        rootView.showSnackbar(R.string.menu_not_available)
    }

    override fun showPhoneNumberNotAvailableError() {
        rootView.showSnackbar(R.string.phone_number_not_available)
    }

    override fun showWebsiteNotAvailableError() {
        rootView.showSnackbar(R.string.website_not_available)
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
