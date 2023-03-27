package com.example.quizgame.data

const val MAX_NO_OF_QUESTIONS = 10
const val SCORE_INCREASE = 1

data class QuizQuestion(
    val question: String,
    var options: List<String>,
    val answer: String
)

val quizQuestions = listOf(
    QuizQuestion(
        question = "What is the capital of Japan?",
        options = listOf("Tokyo", "Kyoto", "Hiroshima", "Osaka"),
        answer = "Tokyo"
    ),
    QuizQuestion(
        question = "Which color is not in the Google logo?",
        options = listOf("Purple", "Red", "Yellow", "Green"),
        answer = "Purple"
    ),
    QuizQuestion(
        question = "What is Harry Potter's scar look likes?",
        options = listOf("Lightning Bolt", "Lightning Mcqueen", "Stick", "Grass"),
        answer = "Lightning Bolt"
    ),
    QuizQuestion(
        question = "What is the currency of India?",
        options = listOf("Rupee", "Dollar", "Euro", "Peso"),
        answer = "Rupee"
    ),
    QuizQuestion(
        question = "How many time zones are there in Russia?",
        options = listOf("11", "9", "10", "12"),
        answer = "11"
    ),
    QuizQuestion(
        question = "How many characters of the English Alphabet?",
        options = listOf("4+20-2+5-1", "24+2-5+4", "26-4+3+2", "8+9+10-2"),
        answer = "4+20-2+5-1"
    ),
    QuizQuestion(
        question = "What are IU's fans called?",
        options = listOf("Uaena", "Paprika", "Aloha", "Alaska"),
        answer = "Uaena"
    ),
    QuizQuestion(
        question = "Which country won the 2022 FIFA World Cup?",
        options = listOf("Argentina", "France", "Brazil", "Germany"),
        answer = "Argentina"
    ),
    QuizQuestion(
        question = "Which team won the Premier League 2019-20?",
        options = listOf("Liverpool", "Manchester City", "Manchester United", "Chelsea"),
        answer = "Liverpool"
    ),
    QuizQuestion(
        question = "Which planet is closest to the sun?",
        options = listOf("Mercury", "Thailand", "Mars", "Venus"),
        answer = "Mercury"
    ),
    QuizQuestion(
        question = "Which planet has the most moons?",
        options = listOf("Saturn", "Mercury", "Mars", "Jupiter"),
        answer = "Saturn"
    ),
    QuizQuestion(
        question = "Who is known as the \"Father of Medicine\"?",
        options = listOf("Hippocrates", "Augustus", "Hippocrene", "Hesiod"),
        answer = "Hippocrates"
    ),
    QuizQuestion(
        question = "Who was the first emperor of Rome?",
        options = listOf("Augustus", "Julius Caesar", "Nero", "Caligula"),
        answer = "Augustus"
    ),
)