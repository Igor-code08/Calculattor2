package com.example.calculattor2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputTime1 = findViewById<EditText>(R.id.inputTime1)
        val inputTime2 = findViewById<EditText>(R.id.inputTime2)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val clearButton = findViewById<Button>(R.id.clearButton)

        addButton.setOnClickListener {
            val time1 = parseTime(inputTime1.text.toString())
            val time2 = parseTime(inputTime2.text.toString())
            val result = time1 + time2
            val resultText = formatTime(result)
            resultTextView.text = resultText
            resultTextView.setTextColor(getColor(R.color.result_text_color))
            Toast.makeText(this, "Результат: $resultText", Toast.LENGTH_SHORT).show()
        }

        subtractButton.setOnClickListener {
            val time1 = parseTime(inputTime1.text.toString())
            val time2 = parseTime(inputTime2.text.toString())
            val result = time1 - time2
            val resultText = formatTime(result)
            resultTextView.text = resultText
            resultTextView.setTextColor(getColor(R.color.result_text_color))
            Toast.makeText(this, "Результат: $resultText", Toast.LENGTH_SHORT).show()
        }

        clearButton.setOnClickListener {
            inputTime1.text.clear()
            inputTime2.text.clear()
            resultTextView.text = ""
            resultTextView.setTextColor(getColor(R.color.default_text_color))
            Toast.makeText(this, "Данные очищены", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseTime(timeString: String): Int {
        var totalSeconds = 0
        val regex = "(\d+h)?(\d+m)?(\d+s)?".toRegex()

        regex.find(timeString)?.destructured?.let { (hours, minutes, seconds) ->
            if (!hours.isNullOrEmpty()) {
                totalSeconds += hours.dropLast(1).toInt() * 3600
            }
            if (!minutes.isNullOrEmpty()) {
                totalSeconds += minutes.dropLast(1).toInt() * 60
            }
            if (!seconds.isNullOrEmpty()) {
                totalSeconds += seconds.dropLast(1).toInt()
            }
        }

        return totalSeconds
    }

    private fun formatTime(totalSeconds: Int): String {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return buildString {
            if (hours > 0) append("${hours}h")
            if (minutes > 0) append("${minutes}m")
            if (seconds > 0 || length == 0) append("${seconds}s")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Приложение закрыто", Toast.LENGTH_SHORT).show()
    }
}