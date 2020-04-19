package com.marzec.cv.views.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.marzec.cv.R
import com.marzec.cv.base.BaseViewHolder
import com.marzec.cv.common.Image
import com.marzec.cv.common.ImageLoader
import com.marzec.cv.ui.home.BaseAdapterDelegate
import com.marzec.cv.views.model.ListItemView
import kotlinx.android.synthetic.main.view_holder_text_row_with_image.view.*

class TextRowWithImageDelegate(
    private val imageLoader: ImageLoader
) : BaseAdapterDelegate<SectionHeaderDelegate.Model>(R.layout.view_holder_text_row_with_image) {

    override fun suitFor(itemView: ListItemView): Boolean {
        return itemView is Model
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        val context = LayoutInflater.from(parent.context)
        return ViewHolder(
            context.inflate(R.layout.view_holder_text_row_with_image, parent, false)
        )
    }

    private inner class ViewHolder(private val view: View) : BaseViewHolder(view) {
        override fun onBind(item: ListItemView, position: Int) {
            require(item is Model)

            imageLoader.loadImage(item.image, view.image)

            view.topDivider.isVisible = item.showTopDivider
            view.title.text = item.title
            view.title.isVisible = item.title.isNotEmpty()

            view.subtitle.text = item.subtitle
            view.subtitle.isVisible = item.subtitle.isNotEmpty()

            view.description.text = item.text
            view.description.isVisible = item.text.isNotEmpty()
        }
    }

    data class Model(
        val image: Image,
        val title: String = "",
        val subtitle: String = "",
        val text: String = "",
        val showTopDivider: Boolean
    ) : ListItemView
}

