package com.example.mathquizapp

import kotlin.random.Random

class QuestionList(private val questionType: String?) {
    private val questionList = ArrayList<Pair<String, Int>>(10)
    private val questionDataList = ArrayList<Question>(10)

    // Set of allowed difficulty mappings to avoid repetitive code
    private val difficultyMap = mapOf(
        "easy" to { Easy.getQuestions() },
        "medium" to { Medium.getQuestions() },
        "hard" to { Hard.getQuestions() }
    )

    // Function to set the questions based on the difficulty level
    private fun setQuestion() {
        val getQuestionFunc = difficultyMap[questionType] ?: difficultyMap["hard"]
        for (i in 1..10) {
            questionList.add(getQuestionFunc?.invoke() ?: Hard.getQuestions())
        }
    }

    // Generates options including the correct one with randomness
    private fun getOption(position: Int): List<String> {
        val optionList = mutableSetOf<String>()
        val answer = questionList[position].second

        // Handling case where answer is invalid (e.g., 7777777)
        if (answer != 7777777) {
            optionList.add(answer.toString())
            while (optionList.size < 4) {
                val randomOption = (answer + Random.nextInt(-9, 10)).toString()
                optionList.add(randomOption)
            }
        } else {
            optionList.add(Random.nextInt(-100, 100).toString())
            while (optionList.size < 4) {
                val randomOption = Random.nextInt(-100, 100).toString()
                optionList.add(randomOption)
            }
        }

        return optionList.shuffled() // Ensures the final list is randomized
    }

    // Function to generate the final list of Question objects
    fun getQuestionList(): ArrayList<Question> {
        setQuestion()

        for (i in questionList.indices) {
            val optionList = getOption(i)
            val question = Question(
                questionList[i].first,
                questionList[i].second.toString(), // Correct answer as a string
                optionList[0],
                optionList[1],
                optionList[2],
                optionList[3],
                "none" // Placeholder for selected option
            )
            questionDataList.add(question)
        }

        return questionDataList
    }
}
