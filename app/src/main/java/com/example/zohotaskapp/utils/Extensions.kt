package com.example.zohotaskapp.utils

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.zohotaskapp.R
import com.example.zohotaskapp.glide.GlideApp
import com.example.zohotaskapp.glide.SvgSoftwareLayerSetter


fun AppCompatActivity.showMessage(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun AppCompatImageView.loadSvg(url: String) {
    val requestBuilder: RequestBuilder<PictureDrawable?>
    requestBuilder = GlideApp.with(this.context)
        .`as`(PictureDrawable::class.java)
//                            .placeholder(R.drawable.image_loading)
//                            .error(R.drawable.image_error)
        .transition(withCrossFade())
        .listener(SvgSoftwareLayerSetter())

    val uri: Uri = Uri.parse(url)
    requestBuilder.load(uri).into(this)
}


fun Context.noNetworkConnectivityError(): AppResult.Error {
    return AppResult.Error(Exception(this.resources.getString(R.string.no_network_connectivity)))
}

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }