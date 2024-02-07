package com.example.randomnumber

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.randomnumber.ui.MainViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchBox(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val inputNumber by rememberSaveable {
        mutableIntStateOf(0)
    }

    val mutableNumber = remember { mutableIntStateOf(inputNumber) }

    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier,
            text = "Input number and get info:"
        )
        TextField(value = mutableNumber.intValue.toString(), onValueChange = {
            if (it.isNotEmpty()) {
                mutableNumber.intValue = it.toInt()
            } else {
                mutableNumber.intValue = 0
            }
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Button(modifier = Modifier, onClick = {
            viewModel.getInfoByNumber(mutableNumber.intValue)
        }) {
            Row {
                Icon(
                    Icons.Sharp.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(1.dp)
                )
                Text("Search by number")
            }
        }

        Button(modifier = Modifier, onClick = {
            viewModel.getInfoByRandomNumber()
        }) {
            Row {
                Icon(
                    Icons.Sharp.Info,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(1.dp)
                )
                Text("Get random number info")
            }
        }
    }
}