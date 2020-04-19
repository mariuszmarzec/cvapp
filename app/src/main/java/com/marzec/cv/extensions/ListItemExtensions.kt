package com.marzec.cv.extensions

import com.marzec.cv.views.model.ListItemView

operator fun MutableList<ListItemView>.plusAssign(item: ListItemView) {
    this.add(item)
}
