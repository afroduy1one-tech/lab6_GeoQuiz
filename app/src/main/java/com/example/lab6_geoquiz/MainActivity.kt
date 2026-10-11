package com.example.lab6_geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab6_geoquiz.ui.theme.Lab6_GeoQuizTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6_GeoQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class QuizQuestion(
    val text: String,
    val correctAnswer: Boolean
)

val questions = listOf(
    QuizQuestion("Canberra is the capital of Australia.", true),
    QuizQuestion("The Pacific Ocean is larger than the Atlantic Ocean.", true),
    QuizQuestion("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
    QuizQuestion("The source of the Nile River is in Egypt.", false),
    QuizQuestion("The Amazon River is the longest river in the Americas.", true),
    QuizQuestion("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
)

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "GeoQuiz",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color(0xFF6200EE))
                .padding(16.dp)
        )

        Text(
            text = questions[currentQuestionIndex].text,
            fontSize = 22.sp,
            modifier = Modifier.padding(24.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {}) {
                Text("TRUE")
            }

            Button(onClick = {}) {
                Text("FALSE")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    if (currentQuestionIndex < questions.lastIndex) {
                        currentQuestionIndex++
                    }
                }
            ) {
                Text("NEXT")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab6_GeoQuizTheme {
        Greeting()
    }
}