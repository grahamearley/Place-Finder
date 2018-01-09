package website.grahamearley.placefinder

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewCompat
import android.support.v7.content.res.AppCompatResources
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Utility extension functions for various classes.
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

fun View.setElevation(@DimenRes dimenRes: Int) {
    val elevation = this.context.resources.getDimensionPixelSize(dimenRes).toFloat()
    ViewCompat.setElevation(this, elevation)
}

fun ImageView.loadImage(url: String) {
    Picasso.with(context)
            .load(url)
            .error(AppCompatResources.getDrawable(context, R.drawable.ic_error_gray_32dp))
            .fit()
            .centerCrop()
            .into(this)
}

fun Activity.hideSoftKeyboard() {
    val view = this.currentFocus

    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Context.showToast(@StringRes stringRes: Int) {
    Toast.makeText(this, stringRes, Toast.LENGTH_LONG).show()
}

fun Activity.startActivityWithTransitionIfPossible(intent: Intent) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    } else {
        startActivity(intent)
    }
}

fun View.showSnackbar(@StringRes stringRes: Int) {
    Snackbar.make(this, stringRes, Snackbar.LENGTH_LONG).show()
}