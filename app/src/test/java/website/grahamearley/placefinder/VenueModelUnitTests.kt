package website.grahamearley.placefinder

import org.junit.Test

import org.junit.Assert.*

/**
 * Unit tests for the [Venue] data model's methods.
 */
class VenueModelUnitTests {

    private val featuredPhoto1 = PhotoItem(id = "1", createdAt = 0,
            prefix = "www.grahamearley.website", suffix = "/picture.jpg",
            width = 100, height = 100,
            user = null, visibility = "visible")

    private val featuredPhoto2 = PhotoItem(id = "2", createdAt = 0,
            prefix = "www.grahamearley.website", suffix = "/picture.png",
            width = 200, height = 500,
            user = null, visibility = "invisible")

    private val populatedFeaturedPhotos = FeaturedPhotos(count = 2, items = listOf(featuredPhoto1, featuredPhoto2))

    private val emptyFeaturedPhotos = FeaturedPhotos(count = 0, items = emptyList())

    private val nullFeaturedPhotos = FeaturedPhotos(count = null, items = null)

    private val featuredPhotosNullList = FeaturedPhotos(count = 3, items = listOf(null, null, null))

    @Test
    fun venue_getFeaturedPhoto_isCorrectForPopulatedList() {
        val venueWithPopulatedPhotos = Venue(featuredPhotos = populatedFeaturedPhotos)
        assertEquals(featuredPhoto1, venueWithPopulatedPhotos.getFirstFeaturedPhotoOrNull())
    }

    @Test
    fun venue_getFeaturedPhoto_isNullForListOfNulls() {
        val venueWithListOfNulls = Venue(featuredPhotos = featuredPhotosNullList)
        assertEquals(null, venueWithListOfNulls.getFirstFeaturedPhotoOrNull())
    }

    @Test
    fun venue_getFeaturedPhoto_isNullForNullList() {
        val venueWithNullList = Venue(featuredPhotos = nullFeaturedPhotos)
        assertEquals(null, venueWithNullList.getFirstFeaturedPhotoOrNull())
    }

    @Test
    fun venue_getFeaturedPhoto_isNullForEmptyList() {
        val venueWithEmptyList = Venue(featuredPhotos = emptyFeaturedPhotos)
        assertEquals(null, venueWithEmptyList.getFirstFeaturedPhotoOrNull())
    }
}
