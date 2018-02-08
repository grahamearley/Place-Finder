package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Category(
		val id: String? = null,
		val name: String? = null,
		val pluralName: String? = null,
		val shortName: String? = null,
		val icon: Icon? = null
): Parcelable