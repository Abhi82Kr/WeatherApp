package com.example.weather.API


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {

    private val weatherAPI = RetrofitInstance.weatherAPI

    private val _weatherResult= MutableLiveData<NetworkResponse<WeatherModel>>()

    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult


    fun getData(city:String){
        _weatherResult.value=NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherAPI.getWeather(constant.apiKey,city)
                // it is suspand function so it will take some time to get data --> we have wrap in viewModelScope

                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value=NetworkResponse.Success(it)
                    }

                }else{
                    _weatherResult.value=NetworkResponse.Error("Failed to load data")
                }
            }catch (e:Exception){
                _weatherResult.value=NetworkResponse.Error("Failed to load data")
            }

        }

    }



}