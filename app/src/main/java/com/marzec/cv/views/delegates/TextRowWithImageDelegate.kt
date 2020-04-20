package com.marzec.cv.views.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.marzec.cv.R
import com.marzec.cv.base.BaseViewHolder
import com.marzec.cv.common.Image
import com.marzec.cv.common.ImageLoader
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

            with (view) {
                imageLoader.loadImage(item.image, image)

                topDivider.isVisible = item.showTopDivider
                title.text = item.title
                title.isVisible = item.title.isNotEmpty()

                subtitle.text = item.subtitle
                subtitle.isVisible = item.subtitle.isNotEmpty()

                description.text = item.text
                description.isVisible = item.text.isNotEmpty()
            }
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

