package com.atitto.redditparse.presentation.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.atitto.redditparse.presentation.common.toDateString
import com.bumptech.glide.Glide

object PostsBinding {

    @JvmStatic
    @BindingAdapter("app:date")
    fun formatDate(textView: TextView, date: Long?) {
        textView.text = date?.toDateString(textView.context)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun formatDate(imageView: ImageView, src: String?) {
        Glide.with(imageView.context).load(src).into(imageView)
    }

}