package com.example.hissesenetleritahmin

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.hissesenetleritahmin.databinding.ActivityProfilBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Suppress("DEPRECATION")
class ProfilActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfilBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = this.database?.reference!!.child("profile")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Bottom Navigation View
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.haberler -> {
                    openWebPage("https://www.cnnturk.com/ekonomi-haberleri/")
                    true
                }
                R.id.güncelkur -> {
                    openWebPage("https://www.cnnturk.com/finans")
                    true
                }
                R.id.cikis -> {
                    auth.signOut()
                    startActivity(Intent(this@ProfilActivity, GirisActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        var currentUser = auth.currentUser
        /*binding.profilemail.text = currentUser?.email*/

        //realtime - databasedeki Id ye ulaşıp id'lerin içindeki veriyi sayfaya aktarma
        var userReference = databaseReference?.child(currentUser?.uid!!)
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.profiladsoyad.text = snapshot.child("adisoyadi").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profilmenu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var currentUser = auth.currentUser
        when (item!!.itemId) {

            //Bilgileri güncelle işlemi
            R.id.bilgilerimigüncelle -> {
                startActivity(Intent(this@ProfilActivity, GuncelleActivity::class.java))
                finish()
            }

            //Hesap silme işlemi
            R.id.hesabısil -> {
                currentUser?.delete()?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(applicationContext, "Hesabınız silindi", Toast.LENGTH_LONG).show()
                        auth.signOut()
                        startActivity(Intent(this@ProfilActivity, GirisActivity::class.java))
                        finish()
                        //kullanıcı bilgilerini RealtimeDatabase içinden silme
                        var currentUserDb =
                            currentUser?.let { it1 -> databaseReference?.child(it1.uid) }
                        currentUserDb?.removeValue()
                    }
                }
            }
            //Müşteri Destek
            R.id.destek -> {
                startActivity(Intent(this@ProfilActivity, MusteriDestekActivity::class.java))
                return true
            }
            //Sıkça Sorulan Sorular
            R.id.SSS -> {
                startActivity(Intent(this@ProfilActivity, SSSActivity::class.java))
                return true
            }
            //Sözlük
            R.id.sözlük -> {
                startActivity(Intent(this@ProfilActivity, SozlukActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No application can handle this request.", Toast.LENGTH_SHORT).show()
        }
    }
}