package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Price(
		val tier: Int? = null,
		val message: String? = null,
		val currency: String? = null
): Parcelable