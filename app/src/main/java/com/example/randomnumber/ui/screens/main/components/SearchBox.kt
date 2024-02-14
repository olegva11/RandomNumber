package com.example.randomnumber.ui.screens.main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.randomnumber.R
import com.example.randomnumber.ui.screens.main.MainViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchBox(viewModel: MainViewModel) {
    val inputNumber by rememberSaveable {
        mutableLongStateOf(0)
    }
    val inputNumberPrev by rememberSaveable {
        mutableLongStateOf(0)
    }

    val mutableNumber = remember { mutableLongStateOf(inputNumber) }

    var isZero by remember { mutableStateOf(true) }

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

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = mutableNumber.longValue.toString(),
                onValueChange = { text ->

                    if (text.all { it.isDigit() }) {
                        if (isZero) {
                            mutableNumber.longValue = text.trim { it == '0' }.toLong()
                            isZero = false
                        } else {
                            mutableNumber.longValue = text.toLongOrNull() ?: 0L
                        }
                    }
                    if (text.isEmpty()) {
                        isZero = true
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