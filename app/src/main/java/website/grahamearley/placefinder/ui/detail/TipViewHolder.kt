package website.grahamearley.placefinder.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item_tip.view.*

class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userImageView: ImageView = itemView.userImageView
    val userNameTextView: TextView = itemView.userNameTextView
    val tipTextView: TextView = itemView.tipTextView
    val tipDateTextView: TextView = itemView.tipDateTextView
}