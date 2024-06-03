package com.example.hissesenetleritahmin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hissesenetleritahmin.databinding.ActivityParolaSifirlaBinding
import com.google.firebase.auth.FirebaseAuth

class ParolaSifirlaActivity : AppCompatActivity() {
    lateinit var binding: ActivityParolaSifirlaBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityParolaSifirlaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.psifirlamabutton.setOnClickListener {
            var psifirlamaemail = binding.psifirlamaemail.text.toString().trim()//mailin başında ve sonunda boşluk hatası versa bunu kaldırır
            if(TextUtils.isEmpty(psifirlamaemail)){
                binding.psifirlamaemail.error = "Lütfen e-mail adresinizi yazınız."
            }else {
                auth.sendPasswordResetEmail(psifirlamaemail)
                    .addOnCompleteListener(this){ sifirlama ->
                        if (sifirlama.isSuccessful){
                            binding.psifirlamamesaj.text = "E-mail adresinize sıfırlama bağlantısı gönderildi. Lütfen kontrol ediniz."
                        }else{
                            binding.psifirlamamesaj.text = "Sıfırlama işlemi başarısız."
                        }

                    }
            }
        }

        //Giriş sayfasına gitme
        binding.psifirlamagirisyapbutton.setOnClickListener {
            intent = Intent(applicationContext,GirisActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}