package com.example.mathquizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mathquizapp.databinding.QuestionItemBinding

class CustomAdapter(val context: Context, val mList: ArrayList<Question>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(QuestionItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val question = mList[position]
        holder.binding.question.text = question.problem
        holder.binding.option1.text = question.option1
        holder.binding.option2.text = question.option2
        holder.binding.option3.text = question.option3
        holder.binding.option4.text = question.option4
        mark(holder, question.selectedOption, question.answer)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder(val binding: QuestionItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
    private fun mark(holder: MyViewHolder, selected: String, answer: String) {
        val options = listOf(holder.binding.option1, holder.binding.option2, holder.binding.option3, holder.binding.option4)

        for (option in options) {
            if (option.text == selected) {
                option.setBackgroundColor(
                    if (selected == answer) context.resources.getColor(R.color.greenColor)
                    else context.resources.getColor(R.color.red)
                )
            } else if (option.text == answer) {
                option.setBackgroundColor(context.resources.getColor(R.color.greenColor))
            }
        }
    }

}