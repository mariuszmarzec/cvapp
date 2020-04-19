package com.marzec.cv.views.delegates

import android.view.ViewGroup
import android.widget.ListView
import com.marzec.cv.base.BaseViewHolder
import com.marzec.cv.views.model.ListItemView

interface AdapterDelegate<V : ListItemView> {

    fun suitFor(itemView: ListItemView): Boolean

    fun viewType(): Int

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

}
