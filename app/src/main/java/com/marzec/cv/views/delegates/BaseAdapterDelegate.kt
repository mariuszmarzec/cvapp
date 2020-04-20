package com.marzec.cv.views.delegates

import com.marzec.cv.views.model.ListItemView

abstract class BaseAdapterDelegate<V : ListItemView>(
    private val viewType: Int
) : AdapterDelegate<V> {

    override fun viewType() = viewType
}