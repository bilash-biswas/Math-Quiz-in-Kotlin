package com.example.mathquizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mathquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.easyQuiz.setOnClickListener{
            startQuiz("easy")
        }

        binding.mediumQuiz.setOnClickListener{
            startQuiz("medium")
        }

        binding.hardQuiz.setOnClickListener{
            startQuiz("hard")
        }

    }

    private fun startQuiz(questionType : String){
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("questionType", questionType)
        startActivity(intent)
    }
}