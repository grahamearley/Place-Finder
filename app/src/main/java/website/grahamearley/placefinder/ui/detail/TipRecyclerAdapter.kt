package website.grahamearley.placefinder.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import website.grahamearley.placefinder.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Adapter for displaying a list of [Tip]s.
 */
class TipRecyclerAdapter : RecyclerView.Adapter<TipViewHolder>() {

    private var tips: List<Tip> = emptyList()

    fun setTips(tips: List<Tip>) {
        this.tips = tips
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tips.size

    override fun onBindViewHolder(holder: TipViewHolder?, position: Int) {
        val tip = tips[position]

        holder?.apply {
            tip.user?.photo?.getUrl()?.let { imageUrl ->
                userImageView.loadImageRounded(imageUrl)
                userImageView.show()
            } ?: userImageView.hide()

            tip.user?.firstName?.let { name ->
                userNameTextView.text = name
                userNameTextView.show()
            } ?: userNameTextView.hide()

            tip.text?.let { tipText ->
                tipTextView.text = tipText
                tipTextView.show()
            } ?: tipTextView.hide()

            tip.createdAt?.let { createdAtSeconds ->
                val createdAtMillis = createdAtSeconds * 1000
                val date = Date(createdAtMillis)
                val dateString = SimpleDateFormat.getDateInstance().format(date)
                tipDateTextView.text = dateString
                tipDateTextView.show()
            } ?: tipDateTextView.hide()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TipViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_tip,
                parent, false)
        return TipViewHolder(itemView)
    }
}

