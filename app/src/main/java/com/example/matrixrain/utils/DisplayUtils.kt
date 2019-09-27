package com.example.matrixrain.utils

import android.content.res.Resources

private val density = Resources.getSystem().displayMetrics.density

val screenWidthPx
    get() = Resources.getSystem().displayMetrics.widthPixels

val screenHeightPx
    get() = Resources.getSystem().displayMetrics.heightPixels

val screenWidthDp
    get() = screenWidthPx / density

val screenHeightDp
    get() = screenHeightPx / density

class Dp(private val valueDp: Int) {
    val px
        get() = valueDp * density
}
