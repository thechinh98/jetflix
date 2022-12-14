package com.example.jetflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.jetflix.presentation.screens.setting.SettingsViewModel
import com.example.jetflix.presentation.theme.JetflixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        renderUi()
        settingsViewModel.onSettingsChanged.observe(this) { restart() }
    }

    private fun renderUi() = setContent {
        val showSettingDialog = remember { mutableStateOf(false) }
        val systemTheme = isSystemInDarkTheme()
        val isDarkTheme = remember { mutableStateOf(systemTheme) }
        val navController = rememberNavController()
        JetflixTheme(isDarkTheme = isDarkTheme.value) {
            // A surface container using the 'background' color from the theme
            CompositionLocalProvider(LocalNavController provides navController) {
                MainContent(isDarkTheme, showSettingDialog)
            }
        }
    }

    private fun restart() {
        finish()
        startActivity(intent)
    }
}


@Composable
fun FilmItem(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(LightGray)
            .padding(16.dp)
            .size(88.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.TopCenter)
                .clip(
                    RoundedCornerShape(10)
                )
        ) {
//            Image(
//                painter = rememberAsyncImagePainter(model = "https://picsum.photos/300/300"),
//                contentDescription = null,
//            )
            Box(
                modifier = Modifier
                    .background(Black.copy(alpha = 0.2f))
                    .heightIn(100.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Black.copy(alpha = 0.5f))
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                ) {
                    Column() {
                        Text(
                            "Avatar 5",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(bottom = 5.dp),
                            color = MaterialTheme.colors.onPrimary
                        )
                        Row(

                        ) {

                        }
                    }

                }
            }
        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(White)
                .align(Alignment.TopCenter)
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                text = "0.0",
                fontSize = 10.sp,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FilmItemPreview() {
    FilmItem()
}
