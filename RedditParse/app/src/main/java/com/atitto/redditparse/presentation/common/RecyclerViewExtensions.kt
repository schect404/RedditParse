package com.atitto.redditparse.presentation.common

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DividerItemDecoration
import com.atitto.redditparse.R


fun RecyclerView.decorate() {
    val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    decoration.setDrawable(resources.getDrawable(R.drawable.divider))
    addItemDecoration(decoration)
}