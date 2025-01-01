package models

case class Choice(text: String)
case class Question(text: String, choices: List[Choice], correctAnswer: Int)
case class Quiz(title: String, questions: List[Question])
