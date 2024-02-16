package com.example.randomnumber.ui.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.randomnumber.R
import com.example.randomnumber.database.HistoryNumberEntity
import com.example.randomnumber.route.ScreenRoute
import com.example.randomnumber.ui.screens.main.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(viewModel: MainViewModel, navController: NavController) {
    Column {
        SearchBox(viewModel)
        HistoryBox(navController, viewModel.allRecords.collectAsState())
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchBox(viewModel: MainViewModel) {
    val inputNumber by rememberSaveable {
        mutableLongStateOf(0)
    }
    val mutableNumber = remember { mutableLongStateOf(inputNumber) }

    val inputText by rememberSaveable {
        mutableStateOf("0")
    }
    val mutableText = remember { mutableStateOf(inputText) }

    var isCorrect by remember { mutableStateOf(true) }

    Card(modifier = Modifier.padding(30.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.input_number_and_get_info),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                value = mutableText.value,
                maxLines = 3,
                isError = !isCorrect,
                label = {
                    if (!isCorrect) {
                        Text(stringResource(R.string.format_not_correct_enter_number))
                    }
                },
                onValueChange = { text ->

                    mutableText.value = text

                    if (text.all { it.isDigit() }) {
                        try {
                            mutableNumber.longValue = text.toLong()
                            isCorrect = true
                        } catch (e: NumberFormatException) {
                            isCorrect = false
                        }
                    } else {
                        isCorrect = false
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                viewModel.getInfoByNumber(mutableNumber.longValue)
            }) {
                ButtonWithIconCustom(Icons.Sharp.Search, stringResource(R.string.search_by_number))
            }

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                viewModel.getInfoByRandomNumber()
            }) {
                ButtonWithIconCustom(
                    Icons.Sharp.Info,
                    stringResource(R.string.get_random_number_info)
                )
            }
        }
    }
}


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


@Composable
fun ButtonWithIconCustom(icon: ImageVector, text: String) {
    Row {
        Icon(
            modifier = Modifier
                .weight(1f)
                .padding(1.dp),
            imageVector = icon,
            contentDescription = "",
        )
        Text(modifier = Modifier.weight(2f), text = text)
    }
}