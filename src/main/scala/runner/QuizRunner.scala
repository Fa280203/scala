package runner

import models._
import dsl.QuizDSL._

object QuizRunner {

  def validateAnswer(question: Question, answer: Int): Option[String] =
    if (answer == question.correctAnswer) Some("Correct!") else None

  def askQuestion(question: Question): Option[String] = {
    println(question.text)
    question.choices.zipWithIndex.foreach { case (choice, index) =>
      println(s"${index + 1}. ${choice.text}")
    }

    print("Your answer: ")
    val userInput = scala.io.StdIn.readInt() - 1
    validateAnswer(question, userInput)
  }

  def runQuiz(quiz: Quiz): Unit = {
    println(s"Welcome to the quiz: ${quiz.title}")
    val results = quiz.questions.map(askQuestion)
    val score = results.flatten.size
    println(s"Your score: $score/${quiz.questions.size}")
  }
}
