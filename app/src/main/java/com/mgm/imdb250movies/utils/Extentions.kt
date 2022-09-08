package com.mgm.imdb250movies.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Majid-Golmoradi on 9/6/2022.
 * Email: golmoradi.majid@gmail.com
 */

fun View.showInvisible(isShown: Boolean) {
    if (isShown) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

fun RecyclerView.initRecycler(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>){
    this.adapter = adapter
    this.layoutManager = layoutManager
}