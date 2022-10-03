package com.example.jetflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetflix.presentation.theme.JetflixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetflixTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FilmItem()
                }
            }
        }
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

                        ){

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
