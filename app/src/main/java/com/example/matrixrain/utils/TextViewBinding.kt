package com.example.matrixrain.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun TextView.setChar(character: Char?) {
    text = character?.toString()
}