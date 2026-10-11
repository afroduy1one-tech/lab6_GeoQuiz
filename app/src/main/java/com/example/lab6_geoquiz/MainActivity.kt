package com.example.lab6_geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab6_geoquiz.ui.theme.Lab6_GeoQuizTheme

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
    var answered by remember { mutableStateOf(false) }
    var correctAnswers by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    val purple = Color(0xFF6200EE)

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
                .background(purple)
                .padding(16.dp)
        )

        Text(
            text = questions[currentQuestionIndex].text,
            fontSize = 12.sp,
            color = Color.Gray,
            maxLines = 1,
            softWrap = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 28.dp),
            textAlign = TextAlign.Center
        )

        if (!answered) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = {
                        if (questions[currentQuestionIndex].correctAnswer) {
                            correctAnswers++
                        }
                        answered = true

                        if (currentQuestionIndex == questions.lastIndex) {
                            showResult = true
                        }
                    },
                    modifier = Modifier
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = purple
                    )
                ) {
                    Text("TRUE")
                }

                Button(
                    onClick = {
                        if (!questions[currentQuestionIndex].correctAnswer) {
                            correctAnswers++
                        }
                        answered = true

                        if (currentQuestionIndex == questions.lastIndex) {
                            showResult = true
                        }
                    },
                    modifier = Modifier
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = purple
                    )
                ) {
                    Text("FALSE")
                }
            }
        }

        if (currentQuestionIndex < questions.lastIndex) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        currentQuestionIndex++
                        answered = false
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = purple
                    )
                ) {
                    Text("NEXT")
                }
            }
        }

        if (showResult) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text("Результат викторины")
                },
                text = {
                    Text("Правильных ответов: $correctAnswers из ${questions.size}")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            currentQuestionIndex = 0
                            correctAnswers = 0
                            answered = false
                            showResult = false
                        }
                    ) {
                        Text("Начать заново")
                    }
                }
            )
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