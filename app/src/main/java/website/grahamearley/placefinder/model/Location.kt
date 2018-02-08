package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Location(
		val address: String? = null,
		val crossStreet: String? = null,
		val lat: Double? = null,
		val lng: Double? = null,
		val postalCode: String? = null,
		val cc: String? = null,
		val city: String? = null,
		val state: String? = null,
		val country: String? = null,
		val formattedAddress: List<String?>? = null
): Parcelable