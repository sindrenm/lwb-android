@file:OptIn(ExperimentalMaterial3Api::class)

package com.sindrenm.lwb.app.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sindrenm.lwb.app.R

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.home_screen_title)) })
        },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding))
    }
}
