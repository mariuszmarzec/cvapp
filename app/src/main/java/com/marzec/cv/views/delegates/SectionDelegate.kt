package com.marzec.cv.views.delegates

import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.view.isVisible
import com.marzec.cv.R
import com.marzec.cv.base.BaseViewHolder
import com.marzec.cv.views.model.ListItemView
import kotlinx.android.synthetic.main.view_holder_section_header.view.*

class SectionHeaderDelegate : BaseAdapterDelegate<SectionHeaderDelegate.Model>(R.layout.view_holder_section_header) {

    override fun suitFor(itemView: ListItemView): Boolean {
        return itemView is Model
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        val context = LayoutInflater.from(parent.context)
        return ViewHolder(
            context.inflate(R.layout.view_holder_section_header, parent, false)
        )
    }

    private class ViewHolder(private val view: View) : BaseViewHolder(view) {
        override fun onBind(item: ListItemView, position: Int) {
            require(item is Model)
            val context = view.context
            view.topDivider.isVisible = item.showTopDivider
            with(view.text) {
                text = item.title
                setTextColor(context.getColor(item.textColorResId))
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    context.resources.getDimensionPixelSize(item.textSizeResId).toFloat()
                )
                gravity = if (item.center) Gravity.CENTER else Gravity.NO_GRAVITY
                setTypeface(typeface, if (item.bold) Typeface.BOLD else Typeface.NORMAL)
            }
        }
    }

    data class Model(
        val title: String,
        @ColorRes val textColorResId: Int = R.color.black,
        @DimenRes val textSizeResId: Int = R.dimen.default_text_size,
        val bold: Boolean = false,
        val center: Boolean = false,
        val showTopDivider: Boolean = false
    ) : ListItemView
}

