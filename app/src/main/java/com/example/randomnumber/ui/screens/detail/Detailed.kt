package com.example.randomnumber.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.randomnumber.R
import com.example.randomnumber.database.HistoryNumberEntity

@Composable
fun Detailed(navController: NavController, numberItem: HistoryNumberEntity) {

    Scaffold(topBar = { TopBar(navController = navController, numberItem.number.toString()) }) //
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(modifier = Modifier.padding(10.dp), text = numberItem.text)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, titleNumber: String) {

    TopAppBar(title = {
        Text(
            stringResource(R.string.number_is_text, titleNumber),
            fontWeight = FontWeight.SemiBold
        )
    },
        navigationIcon = {
            Box(contentAlignment = Alignment.Center) {
                IconButton(onClick = { navController.popBackStack() }) {
                }
                Icon(imageVector = Icons.Default.ArrowBack, "")
            }
        })
}
