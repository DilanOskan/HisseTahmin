package com.example.hissesenetleritahmin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class AnaSayfaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_sayfa)

        Handler(Looper.getMainLooper()).postDelayed({
            // GirisActivity'ye geçiş yapılıyor
            startActivity(Intent(this, GirisActivity::class.java))
            finish()
        }, 3000) // Splash screen 3 saniye görüntülenecek
    }
}