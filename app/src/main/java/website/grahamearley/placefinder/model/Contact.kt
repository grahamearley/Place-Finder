package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Contact(
		val phone: String? = null,
		val formattedPhone: String? = null,
		val twitter: String? = null
): Parcelable