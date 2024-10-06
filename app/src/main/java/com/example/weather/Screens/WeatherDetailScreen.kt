package com.example.weather.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weather.API.WeatherModel


@Composable
fun  WeatherDetailScreen(data:WeatherModel) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "location",
                modifier = Modifier.size(50.dp)
            )
            Column {

                Text(text=data.location.name, fontSize = 20.sp)
                Text(text=data.location.region, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text=data.location.country, fontSize = 30.sp,)
        }

        Spacer(modifier = Modifier.height(30.dp))
        Text(text= "${data.current.temp_c} °C",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        AsyncImage(
            modifier = Modifier.size(140.dp),
            model="https:${data.current.condition.icon}".replace("64x64","128x128"),
            contentDescription = "Image"
        )
        Text(text= data.current.condition.text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(60.dp))
        Card(
            modifier = Modifier.padding(10.dp),
            elevation = CardDefaults.cardElevation(25.dp)


        ) {
            val day= if(data.current.is_day.toBoolean())"Day" else "Night"
            Column( modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text=day,fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    weatherKeyVal("Humidity",data.current.humidity+"%")
                    weatherKeyVal("UV index",data.current.uv)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    weatherKeyVal("Wind Speed",data.current.wind_kph+" km/h")
                    weatherKeyVal("Pressure",data.current.pressure_mb+" mb")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text("Last Updated :- ${data.current.last_updated}")
            }

        }

    }
}

@Composable
fun weatherKeyVal(key:String,value:String){
    Column(
        modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(key, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(value, fontSize = 20.sp,fontWeight = FontWeight.SemiBold)
    }
}