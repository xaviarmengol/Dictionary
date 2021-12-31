package com.plcoding.dictionary.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ImageCard(
    imageUrl: String, size: Dp, modifier: Modifier = Modifier
        .size(size)
        .padding(4.dp)
) {

    Card(
        shape = MaterialTheme.shapes.small,
        //elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
            ),
            contentDescription = "Image description",
            modifier = modifier
        )
    }

}