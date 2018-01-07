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

    private val category1 = Category(name = "Museum")
    private val category2 = Category(name = "Coffee Shop")

    private val populatedCategories = listOf(category1, category2)
    private val emptyCategories = emptyList<Category?>()
    private val nullCategories = listOf(null, null, null)

    private val locationWithPopulatedAddress = Location(formattedAddress = listOf("1234 Street Lane", "Minneapolis, MN", "USA"))
    private val locationWithAddressOfNulls = Location(formattedAddress = listOf(null, null, null))
    private val locationWithNullAddress = Location(formattedAddress = null)
    private val locationWithEmptyAddress = Location(formattedAddress = emptyList())

    @Test
    fun getFeaturedPhoto_isCorrectForPopulatedList() {
        val venueWithPopulatedPhotos = Venue(featuredPhotos = populatedFeaturedPhotos)
        assertEquals(featuredPhoto1, venueWithPopulatedPhotos.getFirstFeaturedPhotoOrNull())
    }

    @Test
    fun getFeaturedPhoto_isNullForListOfNulls() {
        val venueWithPhotoListOfNulls = Venue(featuredPhotos = featuredPhotosNullList)
        assertEquals(null, venueWithPhotoListOfNulls.getFirstFeaturedPhotoOrNull())
    }

    @Test
    fun getFeaturedPhoto_isNullForNullList() {
        val venueWithNullPhotoList = Venue(featuredPhotos = nullFeaturedPhotos)
        assertEquals(null, venueWithNullPhotoList.getFirstFeaturedPhotoOrNull())
    }

    @Test
    fun getFeaturedPhoto_isNullForEmptyList() {
        val venueWithEmptyPhotoList = Venue(featuredPhotos = emptyFeaturedPhotos)
        assertEquals(null, venueWithEmptyPhotoList.getFirstFeaturedPhotoOrNull())
    }

    @Test
    fun getCategory_isCorrectForPopulatedList() {
        val venueWithPopulatedCategoryList = Venue(categories = populatedCategories)
        assertEquals("Museum", venueWithPopulatedCategoryList.getFirstCategoryOrNull())
    }

    @Test
    fun getCategory_isNullForListOfNulls() {
        val venueWithPopulatedCategoryList = Venue(categories = nullCategories)
        assertEquals(null, venueWithPopulatedCategoryList.getFirstCategoryOrNull())
    }

    @Test
    fun getCategory_isNullForNullList() {
        val venueWithPopulatedCategoryList = Venue(categories = null)
        assertEquals(null, venueWithPopulatedCategoryList.getFirstCategoryOrNull())
    }

    @Test
    fun getCategory_isNullForEmptyList() {
        val venueWithPopulatedCategoryList = Venue(categories = emptyCategories)
        assertEquals(null, venueWithPopulatedCategoryList.getFirstCategoryOrNull())
    }

    @Test
    fun getStreetAddress_isCorrectForPopulatedAddress() {
        val venueWithPopulatedAddress = Venue(location = locationWithPopulatedAddress)
        assertEquals("1234 Street Lane", venueWithPopulatedAddress.getStreetAddressOrNull())
    }

    @Test
    fun getStreetAddress_isNullForAddressOfNulls() {
        val venueWithAddressOfNulls = Venue(location = locationWithAddressOfNulls)
        assertEquals(null, venueWithAddressOfNulls.getStreetAddressOrNull())
    }

    @Test
    fun getStreetAddress_isNullForNullAddress() {
        val venueWithNullAddress = Venue(location = locationWithNullAddress)
        assertEquals(null, venueWithNullAddress.getStreetAddressOrNull())
    }

    @Test
    fun getStreetAddress_isNullForEmptyAddress() {
        val venueWithEmptyAddress = Venue(location = locationWithEmptyAddress)
        assertEquals(null, venueWithEmptyAddress.getStreetAddressOrNull())
    }

    @Test
    fun getStreetAddress_isNullForNullLocation() {
        val venueWithNullLocation = Venue(location = null)
        assertEquals(null, venueWithNullLocation.getStreetAddressOrNull())
    }
}
