package com.example.quizgame.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.quizgame.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var userAnswer by mutableStateOf("")

    private var usedQuestions: MutableList<QuizQuestion> = mutableListOf()
    private lateinit var currentQuestion: QuizQuestion

    init {
        resetGame()
    }

    fun resetGame() {
        usedQuestions.clear()
        _uiState.value = GameUiState(currentQuestion = pickRandomQuestionAndShuffle())
    }

    fun checkAnswer(index: Int) {
        userAnswer = currentQuestion.options[index]

        if (userAnswer.equals(currentQuestion.answer, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            _uiState.update { currentState ->
                currentState.copy(
                    textStatus = "Correct!"
                )
            }
            updateGameState(updatedScore)

        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isUserAnswerWrong = true,
                    textStatus = "Wrong Answer!"
                )
            }
            updateGameState(_uiState.value.score)
        }
    }

    private fun updateGameState(updatedScore: Int) {
        if (usedQuestions.size == MAX_NO_OF_QUESTIONS){
            _uiState.update { currentState ->
                currentState.copy(
                    textStatus = "",
                    isUserAnswerWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    textStatus = "",
                    isUserAnswerWrong = false,
                    currentQuestion = pickRandomQuestionAndShuffle(),
                    currentQuestionCount = currentState.currentQuestionCount.inc(),
                    score = updatedScore
                )
            }
        }
    }

    private fun pickRandomQuestionAndShuffle(): QuizQuestion {
        currentQuestion = quizQuestions.random()

        if (usedQuestions.contains(currentQuestion)) {
            return pickRandomQuestionAndShuffle()
        }
        usedQuestions.add(currentQuestion)
        currentQuestion.options = currentQuestion.options.shuffled() //
        return currentQuestion
    }
}
