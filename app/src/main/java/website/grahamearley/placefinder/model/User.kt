package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class User(
		val id: String,
		val firstName: String? = null,
		val lastName: String? = null,
		val gender: String? = null,
		val photo: PhotoItem? = null,
		val type: String? = null
): Parcelable