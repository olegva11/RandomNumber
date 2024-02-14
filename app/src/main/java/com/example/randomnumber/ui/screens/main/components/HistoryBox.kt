package com.example.randomnumber.ui.screens.main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.randomnumber.R
import com.example.randomnumber.database.HistoryNumberEntity
import com.example.randomnumber.route.ScreenRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HistoryBox(navController: NavController, resultHistoryList: State<List<HistoryNumberEntity>>) {
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.history_title))

        LazyColumn(reverseLayout = true, state = listState) {
            items(resultHistoryList.value.size)
            {
                HistoryItem(numberItem = resultHistoryList.value[it], onDetailedClick = {
                    navController.navigate("${ScreenRoute.DetailScreen.route}/${resultHistoryList.value[it].id}")
                })
            }
            CoroutineScope(Dispatchers.Main).launch {
                listState.scrollToItem(resultHistoryList.value.size)
            }
        }
    }
}

@Composable
fun HistoryItem(numberItem: HistoryNumberEntity, onDetailedClick: () -> Unit = {}) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onDetailedClick()
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = numberItem.number.toString(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(
                        2.dp, color = MaterialTheme.colorScheme.primary,
                        shape = CardDefaults.shape
                    )
            ) {
                Text(modifier = Modifier.padding(7.dp), text = numberItem.text, maxLines = 1)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    //HistoryItem(numberItem = HistoryNumberEntity("50 is the approximate number of times a mother hen turns her egg in a day so the yolk does not stick to the shell", true, 50, date = "11.02.2024"))
}