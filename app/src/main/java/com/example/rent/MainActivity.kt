package com.example.projet_mobile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rent.ui.login.LoginScreen
import com.example.rent.ui.login.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: LoginViewModel = viewModel()
                    val state by viewModel.state.collectAsState()

                    LoginScreen(
                        state = state,
                        onIntent = { intent -> viewModel.onIntent(intent) },
                        onNavigateNext = {
                            Log.d("MainActivity", "Success! Ready to navigate.")
                        }
                    )
                }
            }
        }
    }
}