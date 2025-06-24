package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.util.kelvinToFahrenheit
import com.example.myapplication.viewmodel.WeatherViewModel

@Composable
fun ForecastListScreen(
    navController: NavController,
    city: String,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    LaunchedEffect(city) {
        viewModel.fetchForecast(city)
    }

    val forecast by viewModel.forecastState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF5C6BC0))
                    .statusBarsPadding()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = city,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                error != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: $error", color = Color.Red)
                    }
                }
                else -> {
                    forecast?.list?.let { forecastList ->
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray)
                        ) {
                            items(forecastList) { item ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            navController.navigate(
                                                Screen.ForecastDetail.createRoute(
                                                    city,
                                                    item?.main?.temp ?: 0.0,
                                                    item?.main?.feelsLike ?: 0.0,
                                                    item?.weather?.firstOrNull()?.main ?: "",
                                                    item?.weather?.firstOrNull()?.description ?: ""
                                                )
                                            )
                                        }
                                        .padding(horizontal = 16.dp, vertical = 12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item?.weather?.firstOrNull()?.main ?: "",
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "Temp: ${item?.main?.temp?.let { kelvinToFahrenheit(it) }}°F",
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )
                                }
                                Divider(color = Color.Gray, thickness = 1.dp)
                            }
                        }
                    }
                }
            }
        }
    }
}