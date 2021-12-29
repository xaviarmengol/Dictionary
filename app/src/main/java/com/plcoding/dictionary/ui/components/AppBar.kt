package com.plcoding.dictionary.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(title: String,
           icon: ImageVector = Icons.Default.ArrowBack,
           onClick: () -> Unit = {},
           iconAction1: ImageVector? = null,
           onClickAction1: () -> Unit = {},
           iconAction2: ImageVector? = null,
           onClickAction2: () -> Unit = {},
) {
    TopAppBar(
        navigationIcon = {
            Icon(imageVector = icon,"Icon Home",
                modifier = Modifier.padding(horizontal = 12.dp).clickable(onClick = onClick))
        },
        title = { Text(title) },
        actions = {
            if (iconAction1 != null)
                Icon(imageVector = iconAction1,"Icon Action 1",
                    modifier = Modifier.padding(horizontal = 12.dp).clickable(onClick = onClickAction1))

            if (iconAction2 != null)
                Icon(imageVector = iconAction2,"Icon Action 2",
                    modifier = Modifier.padding(horizontal = 12.dp).clickable(onClick = onClickAction2))
        }

    )
}