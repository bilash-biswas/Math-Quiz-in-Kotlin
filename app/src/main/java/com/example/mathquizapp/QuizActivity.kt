package com.example.mathquizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mathquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private var position = 0
    private var timer : CountDownTimer? = null
    private var timeGiven = 0
    private var score = 0
    private var questionDataList = ArrayList<Question>(10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val questionType = intent.getStringExtra("questionType")

        questionDataList = QuestionList(questionType).getQuestionList()
        setGivenTime(questionType)
        updateQuestion()
        updateOption()
        updateHorizontalProgress()
        startTimer()

        binding.option1.setOnClickListener {
            onSelectedOption(binding.option1.text.toString())
        }

        binding.option2.setOnClickListener {
            onSelectedOption(binding.option2.text.toString())
        }

        binding.option3.setOnClickListener {
            onSelectedOption(binding.option3.text.toString())
        }

        binding.option4.setOnClickListener {
            onSelectedOption(binding.option4.text.toString())
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        endQuiz()
    }

    private fun updateQuestion(){
        binding.question.text = questionDataList[position].problem
    }

    private fun updateOption(){
        binding.option1.text = questionDataList[position].option1
        binding.option2.text = questionDataList[position].option2
        binding.option3.text = questionDataList[position].option3
        binding.option4.text = questionDataList[position].option4

    }

    private fun updateHorizontalProgress(){
        binding.progressBar.incrementProgressBy(1)

    }

    private fun setGivenTime(level : String?){
        timeGiven = when(level){
            "easy" -> 10000
            "medium" -> 12000
            else -> 15000
        }
    }

    private fun startTimer(){
        var count = timeGiven / 1000
        binding.circularProgressBar.progress = timeGiven / 1000
        binding.circularProgressBar.max = timeGiven / 1000

        timer = object : CountDownTimer(timeGiven.toLong(), 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.circularProgressBar.incrementProgressBy(-1)
                count--
                binding.countDown.text = count.toString()
            }

            override fun onFinish() {
                setNextRound()
            }

        }.start()
    }

    private fun onSelectedOption(option : String){
        if (option == questionDataList[position].answer){
            score++
        }

        questionDataList[position].selectedOption = option
        setNextRound()
    }

    private fun setNextRound(){
        if (position<9){
            position++
            timer?.cancel()
            timer = null
            updateQuestion()
            updateOption()
            updateHorizontalProgress()
            startTimer()
        }else{
            endQuiz()
        }
    }

    private fun endQuiz() {
        startActivity(Intent(applicationContext, FinishedActivity::class.java)
            .putExtra("score",score).putExtra("data", questionDataList))
        finish()
        timer?.cancel()
        timer = null
        Toast.makeText(applicationContext, "Score : $score/${questionDataList.size}", Toast.LENGTH_SHORT).show()
    }
}