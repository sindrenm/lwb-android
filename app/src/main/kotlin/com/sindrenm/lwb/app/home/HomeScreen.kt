@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.sindrenm.lwb.app.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.sindrenm.lwb.app.R
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun HomeScreen(viewState: HomeViewState) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home_screen_title)) },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Crossfade(viewState, label = "Fading between view states") { viewState ->
            when (viewState) {
                is HomeViewState.Loading -> LoadingIndicator(innerPadding)
                is HomeViewState.Content -> RecordsList(viewState.records, innerPadding)
            }
        }
    }
}

@Composable
private fun LoadingIndicator(innerPadding: PaddingValues) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), enabled = false)
            .padding(innerPadding),
    ) {
        repeat(4) {
            ListItem(
                overlineText = {
                    PlaceholderText(
                        "March 14, 2022",
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                headlineText = {
                    PlaceholderText(
                        "Barbell Military Press",
                        Modifier.padding(top = 2.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                },
                supportingText = {
                    PlaceholderText(
                        "150.00 kg",
                        Modifier.padding(top = 2.dp),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
            )
        }
    }
}

@Composable
private fun RecordsList(records: List<Record>, innerPadding: PaddingValues) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = innerPadding) {
        items(records) { record ->
            RecordListItem(record.exercise, record.date, record.weight)
        }
    }
}

@Composable
private fun RecordListItem(exercise: String, date: LocalDate, weight: Weight) {
    ListItem(
        overlineText = { Text(date.formatLong()) },
        headlineText = { Text(exercise) },
        supportingText = { Text(weight.formatShort()) },
    )
}

private fun LocalDate.formatLong(): String {
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)

    return formatter.format(toJavaLocalDate())
}

@Composable
private fun Weight.formatShort(): String {
    return stringResource(R.string.x_kg, inKg())
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun PlaceholderText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    val textMeasurer = rememberTextMeasurer()
    val measureResult = textMeasurer.measure(AnnotatedString(text), style)
    val measuredSize = with(LocalDensity.current) { measureResult.size.toSize().toDpSize() }

    Box(modifier) {
        Box(
            modifier = Modifier
                .size(measuredSize)
                .placeholder(
                    visible = true,
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.shimmer(),
                ),
        )
    }
}
