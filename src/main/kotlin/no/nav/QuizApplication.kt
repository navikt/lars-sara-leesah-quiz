package no.nav

import no.nav.db.Database
import no.nav.quizrapid.*
import no.nav.rapid.Answer
import no.nav.rapid.Assessment
import no.nav.rapid.Question


/**
 * QuizApplication
 *
 * Her skal teamet bygge ut funksjonalitet for å løse oppgavene i leesah-game.
 */
class QuizApplication(private val teamName: String, database: Database? = null): QuizParticipant(teamName) {

    override fun handle(question: Question) {
        logger.log(question)
        if (question.category == "team-registration") handleRegisterTeam(question)
    }


    override fun handle(assessment: Assessment) {
        logger.log(assessment)
    }

    override fun handle(answer: Answer) {
        logger.log(answer)
    }

    /**
     * Spørsmål handlers
     */

    private fun handleRegisterTeam(question: Question) {
        val regnUt:Int = regnUt(question.question)
        answer(question.category, questionId = question.id(), answer = regnUt.toString())
    }
//[Question] category: arithmetic,
// question: 99 * 63 (svaret må rundes til int),
// id: 70e8d7db-110e-43f1-9b47-4b992196c526

    private fun regnUt(question: String): Int {
        val a = Integer.parseInt(question.subSequence(0, 2).toString())
        val b = Integer.parseInt(question.subSequence(5, 7).toString())

        val regnesymbol = question.subSequence(3, 4);

        return when (regnesymbol) {
            "+" -> a+b
            "-" -> a-b
            "*" -> a*b
            "/" -> a/b
            else -> 0
        }

    }

}
