package com.marzec.cv.common

import android.widget.ImageView
import com.marzec.cv.R
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
            is ResourceImage -> targetImageView.setImageDrawable(
                targetImageView.context.getDrawable(
                    R.drawable.ic_point_black_24dp
                )
            )
        }
    }
}