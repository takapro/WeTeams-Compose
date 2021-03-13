package com.example.weteams.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProcessingBox(
    isProcessing: State<Boolean>,
    content: @Composable BoxScope.() -> Unit
) {
    Box {
        content()

        if (isProcessing.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = contentColorFor(MaterialTheme.colors.background)
                            .copy(alpha = 0.2F)
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun ProcessingColumn(
    isProcessing: State<Boolean>,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    ProcessingBox(isProcessing) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}

@Composable
fun ProcessingLazyColumn(
    isProcessing: State<Boolean>,
    content: LazyListScope.() -> Unit
) {
    ProcessingBox(isProcessing) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}
