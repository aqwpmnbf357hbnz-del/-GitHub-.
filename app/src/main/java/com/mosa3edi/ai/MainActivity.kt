package com.mosa3edi.ai

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var questionInput: EditText
    private lateinit var answerText: TextView
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionInput = findViewById(R.id.questionInput)
        answerText = findViewById(R.id.answerText)
        val askButton: Button = findViewById(R.id.askButton)
        val speakButton: Button = findViewById(R.id.speakButton)

        textToSpeech = TextToSpeech(this, this)

        askButton.setOnClickListener {
            val question = questionInput.text.toString().trim()

            answerText.text = if (question.isEmpty()) {
                "اكتب سؤالك أولًا 🙂"
            } else {
                "هذه نسخة تجريبية من مساعدي الذكي. سنربطها بالذكاء الاصطناعي في الخطوة التالية."
            }
        }

        speakButton.setOnClickListener {
            val answer = answerText.text.toString()
            if (answer.isNotBlank()) {
                textToSpeech.speak(answer, TextToSpeech.QUEUE_FLUSH, null, "answer")
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.language = Locale("ar")
        }
    }

    override fun onDestroy() {
        textToSpeech.stop()
        textToSpeech.shutdown()
        super.onDestroy()
    }
}
