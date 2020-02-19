package com.example.analyzequranupdated

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager


class RtlGridLayoutManager(
    context: Context?,
    spanCount: Int,
    orientation: Int,
    reverseLayout: Boolean
) : GridLayoutManager(context, spanCount, orientation, reverseLayout) {

    override fun isLayoutRTL(): Boolean {
        return true
    }
}