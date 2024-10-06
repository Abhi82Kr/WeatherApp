package com.example.weather.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.weather.API.NetworkResponse
import com.example.weather.API.WeatherViewModel


@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {

    var city= remember { mutableStateOf("") }
    val weatherResult= weatherViewModel.weatherResult.observeAsState()
    val keyboardController= LocalSoftwareKeyboardController.current
    Column(
        modifier =  Modifier.fillMaxSize().padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly


            ){
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city.value,
                onValueChange = {city.value=it},
                label = { Text("Search City")
                }
            )
            IconButton(onClick = {
                weatherViewModel.getData(city.value)
                keyboardController?.hide()
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }

        }

        when(val result = weatherResult.value){
            is NetworkResponse.Error -> {
                Text(text=result.message)
            }
            is NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                WeatherDetailScreen(result.data)
            }
            else -> {}
        }
    }

}

