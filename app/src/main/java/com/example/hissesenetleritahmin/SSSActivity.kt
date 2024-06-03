package com.example.hissesenetleritahmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private var Nothing?.adapter: SoruAdapter
    get() {
        TODO("Not yet implemented")
    }
    set(value) {}

class SSSActivity : AppCompatActivity() {

    val soruList = ArrayList<Soru>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sssactivity)

        initData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val soruAdapter = SoruAdapter(soruList)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) // RecyclerView'i findViewById ile bulun
        recyclerView.layoutManager = LinearLayoutManager(this) // LayoutManager'i ayarla
        recyclerView.adapter = soruAdapter
        recyclerView.setHasFixedSize(true)

    }

    private fun initData() {
        soruList.add(
            Soru(
            soru = "Borsa nedir?",
            cevap = "Borsa, hisse senetleri, tahviller ve diğer finansal enstrümanların alınıp satıldığı organize piyasalardır."
        ))
        soruList.add(
            Soru(
                soru = "Hisse senedi nedir ve nasıl alabilirim?",
                cevap = "Hisse Hisse senedi, bir şirkete ortak olmanızı sağlayan yatırım aracıdır. Borsa uygulamaları üzerinden hisse senedi almak için bir hesap oluşturmanız ve para yatırmanız yeterlidir."
            )
        )
        soruList.add(
            Soru(
                soru = "Temettü nedir ve nasıl alabilirim?",
                cevap = "Temettü, şirketlerin kârlarının bir kısmını hisse senedi sahiplerine dağıtmasıdır. Temettü almaya hak kazanmak için, temettü dağıtım tarihinden önce ilgili şirketin hisse senedine sahip olmanız gerekir."
            )
        )
        soruList.add(
            Soru(
                soru = "Portföy nedir ve nasıl oluşturabilirim?",
                cevap = "Portföy Portföy, bir kişinin veya kurumun sahip olduğu çeşitli yatırım araçlarının toplamıdır. Portföy oluşturmak için hisse senetleri, tahviller ve diğer yatırım araçlarını çeşitlendirebilirsiniz."
            )
        )
        soruList.add(
            Soru(
                soru = "Riskten korunma (hedge) nedir ve nasıl yapılır?",
                cevap = "Riskten korunma, yatırımcıların, yatırım risklerini azaltmak için yaptıkları işlemlerdir. Örneğin, dövizde değer kaybı riskine karşı dövizde satış pozisyonu açabilirsiniz."
            )
        )
        soruList.add(
            Soru(
                soru = "Yatırım fonu nedir ve nasıl yatırım yapabilirim?",
                cevap = "Yatırım fonu, birçok yatırımcının parasını bir araya getirip, profesyonel bir yönetici tarafından yönetilen fondur. Yatırım fonlarına yatırım yapmak için borsa uygulamamızdan fon alım işlemi gerçekleştirebilirsiniz."
            )
        )
        soruList.add(
            Soru(
                soru = "Geçmiş performansınız nasıl ve makine öğrenmesi burada nasıl rol oynuyor?",
                cevap = "Geçmiş performansımızı değerlendirirken, makine öğrenmesi algoritmalarının doğruluk oranlarına ve geriye dönük test sonuçlarına bakarız. Bu algoritmaların geçmiş verilere dayalı tahminlerinin başarısı, gelecekteki performansımızın bir göstergesidir."
            )
        )
        soruList.add(
            Soru(
                soru = "Uygulamayı kullanırken güvenlik önlemleri nelerdir?",
                cevap = "Uygulamayı kullanırken hesap bilgilerinizi ve kişisel bilgilerinizi korumak için güvenlik önlemlerini takip etmelisiniz. Güçlü bir şifre kullanmak, iki faktörlü kimlik doğrulamayı etkinleştirmek ve kamuya açık Wi-Fi ağlarını kullanmamak gibi adımlar alabilirsiniz.i"
            )
        )
        soruList.add(
            Soru(
                soru = "Yatırım stratejileri nelerdir ve nasıl belirlenir?",
                cevap = "Yatırım stratejileri, kişisel hedeflerinize, risk toleransınıza ve piyasa koşullarına bağlı olarak değişir. Buy and hold, day trading, value investing gibi çeşitli stratejiler bulunur. Stratejinizi belirlerken dikkatlice düşünmek önemlidir."
            )
        )
        soruList.add(
            Soru(
                soru = "Kullanıcı geri bildirimlerini nasıl değerlendiriyorsunuz?",
                cevap = "Kullanıcı geri bildirimlerini düzenli olarak izler ve analizlerimizi ve kullanıcı deneyimimizi iyileştirmek için bu geri bildirimleri dikkate alırız. Kullanıcı memnuniyeti ve ihtiyaçlarına odaklanarak uygulamamızı geliştiririz."
            )
        )
    }
}


