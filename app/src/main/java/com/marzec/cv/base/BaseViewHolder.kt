package com.marzec.cv.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.marzec.cv.views.model.ListItemView

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun onBind(item: ListItemView, position: Int) = Unit
}