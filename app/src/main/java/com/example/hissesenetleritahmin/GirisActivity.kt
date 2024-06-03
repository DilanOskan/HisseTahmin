package com.example.hissesenetleritahmin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hissesenetleritahmin.databinding.ActivityGirisBinding
import com.google.firebase.auth.FirebaseAuth

class GirisActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityGirisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)       //activity_giris.xml tasarım sayfası çalışacak
        auth = FirebaseAuth.getInstance()
        //kullanıcının oturum açıp açmadığını kontrol etme
        //kullanıcı daha önce giriş yapmışsa kullanıcıyı direk profil sayfasına yönledirir
        var currentUser = auth.currentUser
        if (currentUser != null){
            startActivity(Intent(this@GirisActivity,ProfilActivity::class.java))
            finish()
        }

        //giris yap butonnuna tıklama
        binding.girisyapbutton.setOnClickListener {
            var girisemail = binding.girisemail.text.toString()
            var girisparola = binding.girisparola.text.toString()
            if (TextUtils.isEmpty(girisemail)) {
                binding.girisemail.error = "Lütfen e-mail adresinizi giriniz."
                return@setOnClickListener
            } else if (TextUtils.isEmpty(girisparola)) {
                binding.girisparola.error = "Lütfen parolanızı giriniz."
                return@setOnClickListener
            }

            //Giriş bilgilerini doğrulayıp giriş yapma
            auth.signInWithEmailAndPassword(girisemail,girisparola)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        intent = Intent(applicationContext, ProfilActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext, "Giriş Hatalı, Lütfen tekrar deneyiniz.", Toast.LENGTH_LONG).show()
                    }
                }
        }

        //Yeni üyelik sayfasına gitme
        binding.girisyeniuyelik.setOnClickListener {
            intent = Intent(applicationContext,UyeActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Parolamı unuttum sayfasına gitme
        binding.girisparolaunuttum.setOnClickListener {
            intent = Intent(applicationContext,ParolaSifirlaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}