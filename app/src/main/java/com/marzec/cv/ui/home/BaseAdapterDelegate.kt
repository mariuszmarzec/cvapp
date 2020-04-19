package com.marzec.cv.ui.home

import com.marzec.cv.views.delegates.AdapterDelegate
import com.marzec.cv.views.model.ListItemView

abstract class BaseAdapterDelegate<V : ListItemView>(
    private val viewType: Int
) : AdapterDelegate<V> {

    override fun viewType() = viewType
}