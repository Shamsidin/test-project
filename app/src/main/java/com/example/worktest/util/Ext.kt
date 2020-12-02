package com.example.worktest.util

import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun ImageView.loadFromUrl(url: String?) {
    if (url.orEmpty().isBlank()) {
        return
    }

    Picasso.get()
        .load(url)
        .resize(720, 405)
        .centerCrop()
        .into(this)
}

fun RecyclerView.onOverScroll(triggerCount: Int = 3, action: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                if (layoutManager.findLastVisibleItemPosition() >= layoutManager.itemCount - triggerCount) {
                    action()
                }
            }
        }
    })
}
