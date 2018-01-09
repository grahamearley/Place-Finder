package website.grahamearley.placefinder.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import website.grahamearley.placefinder.R
import website.grahamearley.placefinder.Tip
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailPresenterContract
import website.grahamearley.placefinder.ui.detail.contract.PlaceDetailViewContract

class PlaceDetailActivity : PlaceDetailViewContract, AppCompatActivity() {
    override val presenter: PlaceDetailPresenterContract = PlaceDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)
    }

    override fun setReason(reason: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showReason() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideReason() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVenueImages(urls: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVenueImages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideVenueImages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVenueTips(tips: List<Tip>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVenueTips() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideVenueTips() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVenueName(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVenueName() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideVenueName() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVenueAddress(address: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVenueAddress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideVenueAddress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVenueCategory(category: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVenueCategory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideVenueCategory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRating(rating: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRatingColor(color: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRating() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideRating() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMenuUrl(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMenuButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideMenuButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPhoneNumber(phoneNumber: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPhoneButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hidePhoneButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setWebsite(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showWebsiteButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideWebsiteButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setOnClickListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun launchUrl(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun launchDialer(phoneNumber: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMenuNotAvailableError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPhoneNumberNotAvailableError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showWebsiteNotAvailableError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
