package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class VenueItem (
        val reasons: Reasons? = null,
        val venue: Venue? = null,
        val tips: List<Tip>? = null
): Parcelable