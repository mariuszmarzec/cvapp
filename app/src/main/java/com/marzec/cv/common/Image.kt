package com.marzec.cv.common

import androidx.annotation.DrawableRes

interface Image

data class UrlImage(val url: String) : Image

data class ResourceImage(@DrawableRes val resId: Int) : Image