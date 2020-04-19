package com.marzec.cv.views

import android.widget.ImageView
import com.marzec.cv.views.model.Image
import com.marzec.cv.views.model.UrlImage
import com.squareup.picasso.Picasso
import javax.inject.Inject

interface ImageLoader {

    fun loadImage(image: Image, targetImageView: ImageView)
}

class DefaultImageLoader @Inject constructor(private val picasso: Picasso) : ImageLoader {
    override fun loadImage(image: Image, targetImageView: ImageView) {
        when (image) {
            is UrlImage -> picasso.load(image.url).into(targetImageView)
        }
    }
}