package website.grahamearley.placefinder

/**
 * Data models generated by JsonToKotlinClass: https://github.com/wuseal/JsonToKotlinClass
 */

data class FoursquareResponse(
		val meta: Meta,
		val response: Response
)

data class Meta(
		val code: Int,
		val requestId: String
)

data class Response(
		val headerFullLocation: String,
		val headerLocationGranularity: String,
		val groups: List<VenueGroup>
)

data class VenueGroup(
		val type: String,
		val name: String,
		val items: List<VenueItem>
)

data class VenueItem(
		val reasons: Reasons?,
		val venue: Venue?,
		val tips: List<Tip>?
)

data class Reasons(
		val count: Int,
		val items: List<ReasonItem>
)

data class ReasonItem(
		val summary: String,
		val type: String,
		val reasonName: String
)

data class Venue(
		val id: String? = null,
		val name: String? = null,
		val contact: Contact? = null,
		val location: Location? = null,
		val categories: List<Category?>? = null,
		val verified: Boolean? = null,
		val stats: Stats? = null,
		val url: String? = null,
		val price: Price? = null,
		val hasMenu: Boolean? = null,
		val rating: Double? = null,
		val ratingColor: String? = null,
		val ratingSignals: Int? = null,
		val menu: Menu? = null,
		val allowMenuUrlEdit: Boolean? = null,
		val hours: Hours? = null,
		val photos: Photos? = null,
		val hereNow: HereNow? = null,
		val featuredPhotos: FeaturedPhotos? = null){

    fun getFirstFeaturedPhotoOrNull() = featuredPhotos?.items?.firstOrNull()

    fun getFirstCategoryOrNull() = categories?.firstOrNull()?.name

    fun getStreetAddressOrNull() = location?.formattedAddress?.firstOrNull()

}

data class Price(
		val tier: Int,
		val message: String,
		val currency: String
)

data class Contact(
		val phone: String,
		val formattedPhone: String,
		val twitter: String
)

data class Category(
		val id: String? = null,
		val name: String? = null,
		val pluralName: String? = null,
		val shortName: String? = null,
		val icon: Icon? = null,
		val primary: Boolean? = null
)

data class Icon(
		val prefix: String,
		val suffix: String
)

data class Hours(
		val status: String,
		val isOpen: Boolean,
		val isLocalHoliday: Boolean
)

data class Stats(
		val checkinsCount: Int,
		val usersCount: Int,
		val tipCount: Int
)

data class HereNow(
		val count: Int,
		val summary: String,
		val groups: List<Any>
)

data class FeaturedPhotos(
		val count: Int?,
		val items: List<PhotoItem?>?
)

data class Location(
		val address: String? = null,
		val crossStreet: String? = null,
		val lat: Double? = null,
		val lng: Double? = null,
		val postalCode: String? = null,
		val cc: String? = null,
		val city: String? = null,
		val state: String? = null,
		val country: String? = null,
		val formattedAddress: List<String?>? = null
)

data class Menu(
		val type: String,
		val label: String,
		val anchor: String,
		val url: String,
		val mobileUrl: String
)

data class Photos(
		val count: Int,
		val groups: List<PhotoGroup>
)

data class PhotoGroup(
		val type: String,
		val name: String,
		val count: Int,
		val items: List<PhotoItem>
)

data class PhotoItem(
		val id: String,
		val createdAt: Int,
		val prefix: String,
		val suffix: String,
		val width: Int,
		val height: Int,
		val user: User?,
		val visibility: String) {

    fun getUrl(): String {
        // cap300 => photo size with width or height of 300 (whichever is larger)
        return prefix + "cap300" + suffix
    }

}

data class UserProfilePhoto(
		val prefix: String,
		val suffix: String
)

data class Tip(
		val id: String,
		val createdAt: Int,
		val text: String,
		val type: String,
		val url: String,
		val canonicalUrl: String,
		val logView: Boolean,
		val agreeCount: Int,
		val disagreeCount: Int,
		val user: User
)

data class User(
		val id: String,
		val firstName: String = "",
        val lastName: String = "",
		val gender: String,
		val photo: UserProfilePhoto,
		val type: String = ""
)