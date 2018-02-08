package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Venue(
        val id: String? = null,
        val name: String? = null,
        val contact: Contact? = null,
        val location: Location? = null,
        val categories: List<Category?>? = null,
        val url: String? = null,
        val price: Price? = null,
        val rating: Double? = null,
        val ratingColor: String? = null,
        val ratingSignals: Int? = null,
        val menu: Menu? = null,
        val hours: Hours? = null,
        val photos: Photos? = null,
        val featuredPhotos: FeaturedPhotos? = null) : Parcelable {

    fun getFirstFeaturedPhotoOrNull() = featuredPhotos?.items?.firstOrNull()

    fun getPhotoUrlsOrNull() = photos?.groups?.flatMap { it.items.orEmpty() }?.map(PhotoItem::getUrl)

    fun getFirstCategoryOrNull() = categories?.firstOrNull()?.name

    fun getStreetAddressOrNull() = location?.formattedAddress?.firstOrNull()

}