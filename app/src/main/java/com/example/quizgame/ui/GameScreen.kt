package com.example.quizgame.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.quizgame.ui.theme.QuizGameTheme
import com.example.quizgame.R

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    
    val gameUiState by gameViewModel.uiState.collectAsState()
    
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.my_blue_2))
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp),
            text = stringResource(id = R.string.app_name), fontSize = 30.sp,
            color = colorResource(id = R.color.ivory)
        )
        GameLayout(
            currentQuestion = gameUiState.currentQuestion.question,
        )

        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}

@Composable
fun GameStatus(
    questionCount: Int,
    score: Int,
) {
    Row(
        modifier = Modifier
            .background(
                colorResource(id = R.color.my_blue_2),
                shape = RoundedCornerShape(8.dp)
            )
            .height(40.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .background(
                    colorResource(id = R.color.my_blue_2),
                    shape = RoundedCornerShape(8.dp)
                ),
            text = stringResource(R.string.question_count, questionCount),
            fontSize = 18.sp,
            color = colorResource(id = R.color.ivory),
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .background(
                    colorResource(id = R.color.my_blue_2),
                    shape = RoundedCornerShape(8.dp)
                ),
            text = stringResource(R.string.score, score),
            fontSize = 18.sp,
            color = colorResource(id = R.color.ivory),
            )
    }
}

@Composable
fun GameLayout(
    currentQuestion: String,
    gameViewModel: GameViewModel = viewModel(),

) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .height(600.dp)
            .background(
                colorResource(id = R.color.steel_blue),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = currentQuestion,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(
                        colorResource(id = R.color.my_yellow_orange),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(20.dp, 40.dp, 20.dp, 40.dp),
                color = colorResource(id = R.color.my_blue)
            )
            GameStatus(
                questionCount = gameUiState.currentQuestionCount,
                score = gameUiState.score,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier,

            ) {
                gameUiState.currentQuestion.options.forEachIndexed { index, option ->
                    Button(
                        modifier = Modifier
                            .width(300.dp)
                            .padding(top = 15.dp),
                        onClick = { gameViewModel.checkAnswer(index) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.white),
                            contentColor = colorResource(id = R.color.my_blue)
                        )
                    ) {
                        Text(
                            text = option,
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = { },
        title = { Text(stringResource(R.string.game_over),
            fontSize = 20.sp,
            color = colorResource(id = R.color.my_yellow_orange)
        ) },
        text = { Text(stringResource(R.string.you_scored, score),
            fontSize = 18.sp,
            color = colorResource(id = R.color.my_blue)
        ) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.white),
                    contentColor = colorResource(id = R.color.my_blue)
                )
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(
                onClick = onPlayAgain,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.my_yellow_orange),
                    contentColor = colorResource(id = R.color.my_blue)
                )
            ) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizGameTheme {
        GameScreen()
    }
}
