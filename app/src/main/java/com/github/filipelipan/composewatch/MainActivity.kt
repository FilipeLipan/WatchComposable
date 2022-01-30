package com.github.filipelipan.composewatch


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.github.filipelipan.composewatch.ui.theme.ComposeWatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWatchTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        WatchComposable(
                            modifier = Modifier
                                .height(300.dp)
                                .width(300.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

