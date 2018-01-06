package website.grahamearley.placefinder

import android.support.v7.content.res.AppCompatResources
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by grahamearley on 1/5/18.
 */

fun <T> Call<T>.enqueue(onResponse: (response: Response<T>?) -> Unit,
                        onFailure: (throwable: Throwable?) -> Unit = {}) {
    this.enqueue(object: Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable?) {
            onFailure(t)
        }

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            onResponse(response)
        }

    })
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    Picasso.with(context)
            .load(url)
            .error(AppCompatResources.getDrawable(context, R.drawable.ic_error_gray_32dp))
            .fit()
            .centerCrop()
            .into(this)
}
