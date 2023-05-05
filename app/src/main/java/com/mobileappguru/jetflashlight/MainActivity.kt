package com.mobileappguru.jetflashlight

import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileappguru.jetflashlight.ui.theme.JetFlashLightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        super.onCreate(savedInstanceState)
        setContent {
            JetFlashLightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlashApp(cameraManager = cameraManager)
                }
            }
        }
    }
}

@Composable
fun FlashApp(cameraManager: CameraManager){

    val state = remember{
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color(0xff0a7e8c)

    ){
        Column(modifier = Modifier.background(color = Color(0xff0a7e8c)),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Button(onClick = { state.value=!state.value
                if (state.value) {
                    val rearCamera = cameraManager.cameraIdList[0]

                    cameraManager.setTorchMode(rearCamera, true)
                } else {
                    val rearCamera = cameraManager.cameraIdList[0]

                    cameraManager.setTorchMode(rearCamera, false)
                }

            }, modifier = Modifier.size(250.dp),colors= ButtonDefaults.buttonColors(Color.Cyan), shape = CircleShape,) {
                if(!state.value){
                    Text(text = "ON", color = Color.Blue
                        , fontSize = 25.sp, fontWeight = FontWeight.Bold )
                }else{
                    Text(text = "OFF", color = Color.White
                        , fontSize = 25.sp, fontWeight = FontWeight.Bold )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetFlashLightTheme {
        //FlashApp(cameraManager = cameraManager)
    }
}