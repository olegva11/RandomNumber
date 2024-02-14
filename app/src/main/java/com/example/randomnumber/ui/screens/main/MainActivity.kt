package com.example.randomnumber.ui.screens.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomnumber.ui.screens.main.components.HistoryBox
import com.example.randomnumber.ui.screens.main.components.SearchBox
import com.example.randomnumber.database.HistoryNumberEntity
import com.example.randomnumber.route.ScreenRoute
import com.example.randomnumber.ui.screens.detail.Detailed
import com.example.randomnumber.ui.theme.RandomNumberTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomNumberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InterestingNumbersApp(viewModel, viewModel.allRecords.value)
                }
            }
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun InterestingNumbersApp(viewModel: MainViewModel, allRecords: List<HistoryNumberEntity>) {
    val navController = rememberNavController()


    Navigation(navController, viewModel)
}

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.StartScreen.route
    )
    {
        composable(route = ScreenRoute.StartScreen.route) {
            Column {
                SearchBox(viewModel = viewModel)
                HistoryBox(navController, viewModel.allRecords.collectAsState())
            }
        }
        composable("${ScreenRoute.DetailScreen.route}/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) {

            val index = it.arguments?.getInt("index")
            index.let { idEntity ->
                val numberDetailed = viewModel.allRecords.value.filter { it.id == idEntity }.first()
                Detailed(navController = navController, numberItem = numberDetailed)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    RandomNumberTheme {

    }
}