package com.example.hissesenetleritahmin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MusteriDestekActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musteri_destek)

        val destekbutton: Button = findViewById(R.id.destekbutton)
        val destekeditTextMessage: EditText = findViewById(R.id.destekeditTextMessage)

        destekbutton.setOnClickListener {
            val message = destekeditTextMessage.text.toString()
            if (message.isNotEmpty()) {
                sendEmail(message)
            } else {
                Toast.makeText(this, "Lütfen bir mesaj yazın", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendEmail(message: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("hissetahmin1@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Destek Talebi")
            putExtra(Intent.EXTRA_TEXT, message)
        }

        try {
            startActivity(Intent.createChooser(intent, "E-posta uygulaması seçin"))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, "E-posta uygulaması bulunamadı", Toast.LENGTH_SHORT).show()
        }
    }

}