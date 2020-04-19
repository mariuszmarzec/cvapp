package com.marzec.cv.common

import android.widget.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Inject

interface ImageLoader {

    fun loadImage(image: Image, targetImageView: ImageView)
}

class DefaultImageLoader @Inject constructor(private val picasso: Picasso) :
    ImageLoader {
    override fun loadImage(image: Image, targetImageView: ImageView) {
        when (image) {
            is UrlImage -> picasso.load(image.url).into(targetImageView)
            is ResourceImage -> picasso.load(image.resId).into(targetImageView)
        }
    }
}