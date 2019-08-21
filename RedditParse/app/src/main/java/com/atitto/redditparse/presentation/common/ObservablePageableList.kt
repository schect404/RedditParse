package com.atitto.redditparse.presentation.common

import android.databinding.ObservableArrayList
import android.databinding.ObservableList

class ObservablePageableList<T> {

    val items: ObservableList<T> = ObservableArrayList()
    var nextPage: String? = null

    fun replace(newList: ObservableList<T>) {
        items.clear()
        items.addAll(newList)
    }

    fun loaded(newPage: String?) {
        nextPage = newPage
    }

}