package com.marzec.cv.views.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marzec.cv.base.BaseViewHolder
import com.marzec.cv.views.model.ListItemView

class DelegateAdapter(private val delegateManager: DelegateManager) : RecyclerView.Adapter<BaseViewHolder>() {

    var items by NotifyingRecyclerAdapterDelegate(emptyList<ListItemView>())

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return delegateManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(items[position], position)
    }
}