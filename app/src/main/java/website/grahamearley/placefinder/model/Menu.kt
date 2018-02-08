package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Menu(
		val type: String? = null,
		val label: String? = null,
		val anchor: String? = null,
		val url: String? = null,
		val mobileUrl: String? = null
): Parcelable