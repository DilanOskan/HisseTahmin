package com.example.hissesenetleritahmin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hissesenetleritahmin.databinding.ActivityUyeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UyeActivity : AppCompatActivity() {
    lateinit var binding: ActivityUyeBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  binding = ActivityUyeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()     //kullanıcı bilgilerini kaydetme
        databaseReference = this.database?.reference!!.child("profile")

        //Kaydet butonu ile kayıt yapma
        binding.uyekaydetbutton.setOnClickListener {
            var uyeadsoyad = binding.uyeadsoyad.text.toString()
            var uyeemail = binding.uyeemail.text.toString()
            var uyeparola = binding.uyeparola.text.toString()
            if (TextUtils.isEmpty(uyeadsoyad)) {   //Edittext in içinde veri yoksa eror verir
                binding.uyeadsoyad.error = "Lütfen adınızı ve soyadınızı giriniz"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(uyeemail)) {
                binding.uyeemail.error = "Lütfen e-mail adresinizi giriniz"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(uyeparola)) {
                binding.uyeparola.error = "Lütfen parolanızı giriniz"
                return@setOnClickListener
            }

            //email, parola ve kullanıcı bilgilerini veri tabanına ekleme
            auth.createUserWithEmailAndPassword(binding.uyeemail.text.toString(), binding.uyeparola.text.toString())  //e mail ve parola bilgileri Authentication'a kaydedilir
                .addOnCompleteListener(this){ task ->
                    if (task.isSuccessful) {
                        //Şuanki kullanıcı biliglerini alma
                        var currentUser = auth.currentUser
                        //kullanıcı ID sini alıp bu id altında ad ve soyad kaydedilir
                        var currentUserDb = currentUser?.let { it1 -> databaseReference?.child(it1.uid) }
                        currentUserDb?.child("adisoyadi")?.setValue(binding.uyeadsoyad.text.toString())
                        Toast.makeText(this@UyeActivity,"Kayıt Başarılı",Toast.LENGTH_LONG).show()
                    }else{
                        val errorMessage = task.exception?.message
                        Toast.makeText(this@UyeActivity,"Kayıt Hatalı:$errorMessage",Toast.LENGTH_LONG).show()
                        Log.e("UyeActivity", "Kayıt hatası", task.exception)
                    }

                }
        }

        //Giriş sayfasına gitmek için bağlantı
        binding.uyegirisyapbutton.setOnClickListener {
            intent = Intent(applicationContext,GirisActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}