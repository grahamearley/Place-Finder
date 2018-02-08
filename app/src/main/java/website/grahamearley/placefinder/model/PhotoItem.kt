package website.grahamearley.placefinder.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class PhotoItem(
        val id: String? = null,
        val createdAt: Int? = null,
        val prefix: String? = null,
        val suffix: String? = null,
        val width: Int? = null,
        val height: Int? = null,
        val user: User? = null,
        val visibility: String? = null): Parcelable {

    fun getUrl(): String {
        // cap300 => photo size with width or height of 300 (whichever is larger)
        return prefix + "cap300" + suffix
    }

}