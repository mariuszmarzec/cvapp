package com.marzec.cv

import com.marzec.cv.common.StringProvider

object MockStringProvider : StringProvider {
    override fun getString(resId: Int): String {
        return "string resource from $resId"
    }
}