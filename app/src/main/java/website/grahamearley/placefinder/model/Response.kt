package website.grahamearley.placefinder.model

data class Response(
        val groups: List<VenueGroup>? = null,
        val tips: Tips? = null,
        val photos: Photos? = null
)