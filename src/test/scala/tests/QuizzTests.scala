package tests

import org.scalatest.funsuite.AnyFunSuite
import models.*
import dsl.QuizDSL.*
import jdk.internal.vm.vector.VectorSupport.test

class QuizTest extends AnyFunSuite {

  test("Build a quiz with DSL") {
    val quiz = quiz("Test Quiz") {
      _.question("What is Scala?") { q =>
        q.choice("Language", isCorrect = true)
        q.choice("Game")
      }
    }

    assert(quiz.title == "Test Quiz")
    assert(quiz.questions.size == 1)
    assert(quiz.questions.head.text == "What is Scala?")
  }

  test("Validate correct answer") {
    val question = Question("What is Scala?", List(Choice("Language")), 0)
    assert(validateAnswer(question, 0).contains("Correct!"))
  }
}
