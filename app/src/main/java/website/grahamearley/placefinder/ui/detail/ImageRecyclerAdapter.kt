package website.grahamearley.placefinder.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import website.grahamearley.placefinder.*

/**
 * Adapter for displaying a list of images.
 */
class ImageRecyclerAdapter : RecyclerView.Adapter<ImageViewHolder>() {

    private var imageUrls: List<String> = emptyList()

    fun setImageUrls(imageUrls: List<String>) {
        this.imageUrls = imageUrls
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = imageUrls.size

    override fun onBindViewHolder(holder: ImageViewHolder?, position: Int) {
        val imageUrl = imageUrls[position]

        holder?.apply {
            imageView.loadImage(imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_image,
                parent, false)
        return ImageViewHolder(itemView)
    }
}

