package com.example.androidmemory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidmemory.ui.theme.AndroidMemoryTheme

class MainActivity : ComponentActivity() {

    val idList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        logCurrentMemoryProfile("OnCreate start")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidMemoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        execute()
                    }
                }
            }
        }
        logCurrentMemoryProfile("OnCreate after setContent")

        Thread {
            Thread.sleep((10000))
            logCurrentMemoryProfile("OnCreate end after delay")
        }.start()
    }

    fun execute() {
        for (i in 1..1000000) {
            idList.add(i)
        }
        logCurrentMemoryProfile("execute current idList size : ${idList.size}")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onClick) {
            Text(text = "Execute")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidMemoryTheme {
        Greeting("Android") {}
    }
}