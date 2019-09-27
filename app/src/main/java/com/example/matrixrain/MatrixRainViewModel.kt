package com.example.matrixrain

import android.content.Context
import me.tatarka.bindingcollectionadapter2.ItemBinding
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.matrixrain.utils.screenHeightDp
import com.example.matrixrain.utils.screenWidthDp
import kotlinx.coroutines.*
import kotlin.math.ceil
import kotlin.math.floor

private const val MIN_COLUMN_WIDTH_DP = 10
private const val MIN_COLUMN_HEIGHT_DP = 18

class MatrixRainViewModel(val context: Context) : ViewModel() {

    private val nbColumns = floor(screenWidthDp / MIN_COLUMN_WIDTH_DP).toInt()

    private val nbRows = ceil(screenHeightDp / MIN_COLUMN_HEIGHT_DP).toInt() - 1

    val layoutManager = GridLayoutManager(context, nbColumns)

    val items: ObservableList<MatrixRainCellViewModel> = ObservableArrayList()

    val itemBinding =
        ItemBinding.of<MatrixRainCellViewModel>(BR.viewModel, R.layout.item_matrix_rain_cell)

    init {
        clearScreen()
    }

    private fun clearScreen() {
        items.clear()
        items.addAll((0 until nbRows * nbColumns).map {
            MatrixRainCellViewModel(context)
        })
    }

    fun drawRain() {
        val columnIndexes = (0 until nbColumns).shuffled()
        val waitInterval = (10L..2000L).shuffled()
        val initialDelay = (1000L..15000L).shuffled()

        (0 until nbColumns).forEach { index ->
            viewModelScope.launch {
                drawColumn(columnIndexes[index], waitInterval[index], initialDelay[index])
            }
        }
    }

    private suspend fun drawColumn(
        columnIndex: Int,
        waitInterval: Long,
        initialDelay: Long
    ) {
        delay(initialDelay)
        (0 until nbRows).forEach { rowIndex ->
            val cellIndex = columnIndex + nbColumns * rowIndex
            items[cellIndex].pickRandomLetter()
            delay(waitInterval)
        }
    }
}