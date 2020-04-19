package com.marzec.cv.common

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface StringProvider {

    fun getString(@StringRes resId: Int): String
}

class StringProviderImpl @Inject constructor(
    private val context: Context
) : StringProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

}