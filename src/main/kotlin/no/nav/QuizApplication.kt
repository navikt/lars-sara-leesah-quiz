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
       handleRegisterTeam(question)
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

// [Question] category: NAV,
// question: Hva heter applikasjonsplattformen til NAV?, id: 0df7552c-ab8e-42cc-bd11-a6670116885c

    // [Question] category: NAV,
    // question: På hvilken nettside finner man informasjon om rekruttering til NAV IT?,
    // id: d0bcc96d-30ea-4fd7-a03a-95ddf952c8cd

    // [Question] category: NAV, question: Hva heter NAV-direktøren?, id: eab7f69a-b02a-4e42-9219-b64261ce93ed

    // [Question] category: is-a-prime, question: Er det et primtall? 47, id: df7d58fb-1ca0-49fc-9dcb-ca17ea8b10ba
    private fun handleRegisterTeam(question: Question) {

        if(question.question.contains("svaret må rundes til int"))
            answer(question.category, questionId = question.id(), answer = regnUt(question.question).toString())

        if (question.id() == "0df7552c-ab8e-42cc-bd11-a6670116885c") {
            answer(question.category, questionId = question.id(), answer = "NAIS")
        }
        if (question.id() == "d0bcc96d-30ea-4fd7-a03a-95ddf952c8cd") {
            answer(question.category, questionId = question.id(), answer = "detsombetyrnoe.no")
        }
        if (question.id() == "eab7f69a-b02a-4e42-9219-b64261ce93ed") {
            answer(question.category, questionId = question.id(), answer = "Hans Christian Holte")
        }
        if (question.question.startsWith("Er det et primtall?")) {
            answer(question.category, questionId = question.id(), answer = erDetPrimtall(question.question))
        }
    }

    private fun erDetPrimtall(question: String): String {
        val tall = Integer.parseInt(question.subSequence(20, 22).toString())

            val num = tall
            var flag = false
            for (i in 2..num / 2) {
                // condition for nonprime number
                if (num % i == 0) {
                    flag = true
                    break
                }
            }

            if (!flag)
                return("true")
            else
                return("false")
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

