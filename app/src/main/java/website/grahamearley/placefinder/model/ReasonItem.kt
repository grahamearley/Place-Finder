package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ReasonItem(
		val summary: String? = null,
		val type: String? = null,
		val reasonName: String? = null
): Parcelable