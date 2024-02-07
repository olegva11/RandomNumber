package com.example.randomnumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.randomnumber.ui.MainViewModel
import com.example.randomnumber.ui.theme.RandomNumberTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomNumberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InterestingNumbersApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun InterestingNumbersApp(viewModel: MainViewModel)
{
    Column {
        SearchBox(viewModel = viewModel)
        HistoryBox()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    RandomNumberTheme {

    }
}