package dsl

import models._

object QuizDSL {
  class QuestionBuilder(val text: String) {
    private var choices = List.empty[Choice]
    private var correctAnswer = -1

    def choice(text: String, isCorrect: Boolean = false): Unit = {
      choices = choices :+ Choice(text)
      if (isCorrect) correctAnswer = choices.size - 1
    }

    def build(): Question = Question(text, choices, correctAnswer)
  }

  class QuizBuilder(val title: String) {
    private var questions = List.empty[Question]

    def question(text: String)(buildChoices: QuestionBuilder => Unit): Unit = {
      val builder = new QuestionBuilder(text)
      buildChoices(builder)
      questions = questions :+ builder.build()
    }

    def build(): Quiz = Quiz(title, questions)
  }

  def quiz(title: String)(buildQuestions: QuizBuilder => Unit): Quiz = {
    val builder = new QuizBuilder(title)
    buildQuestions(builder)
    builder.build()
  }
}
