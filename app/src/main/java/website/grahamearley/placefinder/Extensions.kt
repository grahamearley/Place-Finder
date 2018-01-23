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
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap

/**
 * Utility extension functions for various classes.
 */

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

/**
 *  Image rounding solution from here: https://stackoverflow.com/a/37756752/5054197
 */
fun ImageView.loadImageRounded(url: String) {
    Picasso.with(context)
            .load(url)
            .fit()
            .centerCrop()
            .into(this, object : com.squareup.picasso.Callback {
                override fun onError() {
                    this@loadImageRounded.setImageResource(R.drawable.ic_error_gray_32dp)
                }

                override fun onSuccess() {
                    val imageBitmap = (this@loadImageRounded.drawable as BitmapDrawable).bitmap
                    val imageDrawable = RoundedBitmapDrawableFactory.create(resources, imageBitmap)
                    imageDrawable.isCircular = true
                    imageDrawable.cornerRadius = Math.max(imageBitmap.width, imageBitmap.height) / 2.0f
                    this@loadImageRounded.setImageDrawable(imageDrawable)
                }
            })
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