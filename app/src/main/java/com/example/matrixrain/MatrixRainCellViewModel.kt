package com.example.matrixrain

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableChar
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt

private val ALPHABET = ('a'..'z').plus('0'..'9')

data class MatrixRainCellViewModel(val context: Context) {

    val color = ObservableInt(
        ContextCompat.getColor(
            context,
            R.color.matrix_green_100
        )
    )

    val letter = ObservableChar()

    val alpha = ObservableFloat(1f)

    fun pickRandomLetter() {
        letter.set(ALPHABET.shuffled().first())
    }
}