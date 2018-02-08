package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Tip(
		val id: String? = null,
		val createdAt: Long? = null,
		val text: String? = null,
		val type: String? = null,
		val url: String? = null,
		val canonicalUrl: String? = null,
		val agreeCount: Int? = null,
		val disagreeCount: Int? = null,
		val user: User? = null
): Parcelable