package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.WeatherResponse
import com.example.myapplication.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor( val repository: WeatherRepository) : ViewModel(){

    private val _forecastState = MutableStateFlow<WeatherResponse?>(null)
    val forecastState: StateFlow<WeatherResponse?> = _forecastState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchForecast(city: String) {
        viewModelScope.launch (Dispatchers.IO) {
            _isLoading.value = true
            try {
                _forecastState.value = repository.getForecast(city)
            } catch (e: Exception) {
                _forecastState.value = null
            } finally {
                _isLoading.value = false
            }

        }

    }
}