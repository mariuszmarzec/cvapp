package com.marzec.cv.views.delegates

import android.view.ViewGroup
import com.marzec.cv.base.BaseViewHolder
import com.marzec.cv.views.model.ListItemView
import java.lang.IllegalArgumentException

class DelegateManager {

    private val delegates = mutableListOf<AdapterDelegate<out ListItemView>>()

    fun add(adapter: AdapterDelegate<out ListItemView>): DelegateManager {
        delegates.add(adapter)
        return this
    }

    fun getItemViewType(itemView: ListItemView): Int {
        return delegates.firstOrNull { it.suitFor(itemView) }?.viewType()
            ?: throw IllegalArgumentException("Unknown delegate is needed for $itemView")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return delegates.first { it.viewType() == viewType }.onCreateViewHolder(parent)
    }


}
