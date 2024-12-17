package com.example.a18prkrylov

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a18prkrylov.ui.theme._18PrKrylovTheme
import com.meter_alc_rgb.wetherappcompose.ui.theme._18PrKrylovThemeTheme
import org.json.JSONObject
const val API_KEY = "de4ee5fa871f449c97a73206241510"



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _18PrKrylovTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("London", this)
                }
                Image(
                    painter = painterResource(
                        id = R.drawable.weather_bg
                    ),
                    contentDescription = "im1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.FillBounds
                )
                Column{
                    MainCard()
                    TabLayout()
                }

            }
        }
    }
}


@Composable
fun Greeting(name: String, context: Context) {
    val state = remember {
        mutableStateOf("Unknown")
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Temperature in $name: ${state.value} Cº")
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = {
                getData(name, context, state)
            }, modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                Text(text = "Refresh")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    _18PrKrylovTheme {

    }
}

fun getData(name: String, context: Context, mState: MutableState<String>){
    val url = "https://api.weatherapi.com/v1/current.json" +
            "?key=$API_KEY&" +
            "q=$name" +
            "&aqi=no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        {
                response->
            val obj = JSONObject(response)
            val temp = obj.getJSONObject("current")
            mState.value = temp.getString("temp_c")
            Log.d("MyLog","Response: ${temp.getString("temp_c")}")
        },
        {
            Log.d("MyLog","Volley error: $it")
        }
    )
    queue.add(stringRequest)
}
