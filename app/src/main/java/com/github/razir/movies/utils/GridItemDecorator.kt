package com.github.razir.movies.utils

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(drawable: Drawable) : RecyclerView.ItemDecoration() {

    private val dividerHeight = drawable.intrinsicHeight
    private val dividerWidth = drawable.intrinsicWidth

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(dividerWidth, 0, dividerWidth, dividerHeight)
    }
}