package com.antareza.tesdanamon.util

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.antareza.tesdanamon.R
import com.bumptech.glide.Glide

fun log(text: String) {
    Log.d("Splash screen demo", text)
}

fun dp2px(dips: Float): Float = dips * Resources.getSystem().displayMetrics.density + 0.5f

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .error(R.color.gray)
        .into(this)
}

fun setError(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}