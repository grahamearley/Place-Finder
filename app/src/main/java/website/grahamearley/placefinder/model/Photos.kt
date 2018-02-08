package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Photos(
        val count: Int? = null,
        val groups: List<PhotoGroup>? = null,
        val items: List<PhotoItem>? = null
): Parcelable