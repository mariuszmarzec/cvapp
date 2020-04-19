package com.marzec.cv

import com.marzec.cv.common.StringProvider

object MockStringProvider : StringProvider {
    override fun getString(resId: Int): String {
        return "$resId"
    }
}