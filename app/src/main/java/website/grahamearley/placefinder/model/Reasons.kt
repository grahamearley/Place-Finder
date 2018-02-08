package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Reasons(
		val count: Int? = null,
		val items: List<ReasonItem>? = null
): Parcelable