package website.grahamearley.placefinder

import org.junit.Test

import org.junit.Assert.*

/**
 * Unit tests for the [PhotoItem] data model's methods.
 */
class PhotoItemUnitTests {

    private val photoItem = PhotoItem(id = "1", createdAt = 0,
            prefix = "www.grahamearley.website/", suffix = "/picture.jpg",
            width = 100, height = 100,
            user = null, visibility = "visible")

    @Test
    fun getUrl_isCorrect() {
        val sizeOfRequestedImage = "cap300"
        val expectedUrl = "www.grahamearley.website/$sizeOfRequestedImage/picture.jpg"
        assertEquals(expectedUrl, photoItem.getUrl())
    }
}
