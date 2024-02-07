package com.example.randomnumber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBox(modifier: Modifier = Modifier) {
    val inputNumber by remember {
        mutableIntStateOf(0)
    }

    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier,
            text = "Input number and get info:"
        )
        TextField(value = inputNumber.toString(), onValueChange = {})
        Button(modifier = Modifier, onClick = {
        }) {
            Row {
                Icon(
                    Icons.Sharp.Search,
                    contentDescription = "lolka",
                    modifier = Modifier
                        .padding(1.dp)
                )
                Text("Search by number")
            }
        }

        Button(modifier = Modifier, onClick = {
        }) {
            Row {
                Icon(
                    Icons.Sharp.Info,
                    contentDescription = "lolka",
                    modifier = Modifier
                        .padding(1.dp)
                )
                Text("Get random number info")
            }
        }
    }
}