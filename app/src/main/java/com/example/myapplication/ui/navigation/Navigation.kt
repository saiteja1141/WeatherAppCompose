package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.data.model.ForecastItem
import com.example.myapplication.ui.screens.CityLookupScreen
import com.example.myapplication.ui.screens.ForecastDetailScreen
import com.example.myapplication.ui.screens.ForecastListScreen
import com.example.myapplication.viewmodel.WeatherViewModel

sealed class Screen(val route: String) {
    object Lookup : Screen("lookup")
    object ForecastList : Screen("forecast_list/{city}") {
        fun createRoute(city: String) = "forecast_list/$city"
    }

    object ForecastDetail : Screen(
        "forecast_detail/{city}/{temp}/{feelsLike}/{main}/{description}"
    ) {
        fun createRoute(
            city: String,
            temp: Double,
            feelsLike: Double,
            main: String,
            description: String
        ) = "forecast_detail/$city/${temp}/$feelsLike/$main/$description"
    }
}

    @Composable
    fun WeatherNavGraph(navController: NavHostController) {
        NavHost(navController, startDestination = Screen.Lookup.route) {
            composable(Screen.Lookup.route) {
                CityLookupScreen(navController)
            }
            composable(
                Screen.ForecastList.route,
                arguments = listOf(navArgument("city") { type = NavType.StringType })
            ) { backStackEntry ->
                val city = backStackEntry.arguments?.getString("city") ?: ""
                ForecastListScreen(navController, city)
            }
            composable(
                route = Screen.ForecastDetail.route,
                arguments = listOf(
                    navArgument("city") { type = NavType.StringType },
                    navArgument("temp") { type = NavType.StringType },
                    navArgument("feelsLike") { type = NavType.StringType },
                    navArgument("main") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val city = backStackEntry.arguments?.getString("city") ?: ""
                val temp = backStackEntry.arguments?.getString("temp")?.toDoubleOrNull() ?: 0.0
                val feelsLike = backStackEntry.arguments?.getString("feelsLike")?.toDoubleOrNull() ?: 0.0
                val main = backStackEntry.arguments?.getString("main") ?: ""
                val description = backStackEntry.arguments?.getString("description") ?: ""

                ForecastDetailScreen(navController, city, temp, feelsLike, main, description)
            }
        }
    }

