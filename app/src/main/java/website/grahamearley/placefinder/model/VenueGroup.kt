package website.grahamearley.placefinder.model

data class VenueGroup(
		val type: String? = null,
		val name: String? = null,
		val items: List<VenueItem>? = null
)