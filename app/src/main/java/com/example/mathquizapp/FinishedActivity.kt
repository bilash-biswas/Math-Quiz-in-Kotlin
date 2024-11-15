package com.example.mathquizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mathquizapp.databinding.ActivityFinishedBinding

class FinishedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishedBinding
    private lateinit var data: ArrayList<Question>
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.score.text = "Your score\n"+intent.getIntExtra("score", 0).toString()+"/10"
        data  = intent.getSerializableExtra("data") as ArrayList<Question>

        adapter = CustomAdapter(applicationContext, data)
        binding.allProblem.layoutManager = LinearLayoutManager(this)
        binding.allProblem.setHasFixedSize(true)
        binding.allProblem.adapter = adapter

        binding.home.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
}