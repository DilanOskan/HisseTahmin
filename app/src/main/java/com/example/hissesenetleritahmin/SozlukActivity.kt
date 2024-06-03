package com.example.hissesenetleritahmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class SozlukActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<HarfData>()
    private lateinit var adapter: HarfAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sozluk)

        recyclerView = findViewById(R.id.recyclerView1)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = HarfAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {

        if (query != null){
            val filteredList = ArrayList<HarfData>()
            for (i in mList){
                if (i.title.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()){
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            }else{
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(
            HarfData(
                "A",
                "Arbitraj: Farklı piyasalardaki fiyat farklarından yararlanarak risksiz kâr elde etme işlemidir."
            )
        )
        mList.add(
            HarfData(
                "B",
                "Bono: Kısa vadeli borçlanma aracı olup, genellikle devletler veya büyük şirketler tarafından çıkarılır. Örneğin, 6 ay vadeli bir bono aldığınızda, vade sonunda ana paranızı geri alır ve belirli bir faiz geliri elde edersiniz."
            )
        )
        mList.add(
            HarfData(
                "C",
                " "
            )
        )
        mList.add(
            HarfData(
                "D",
                " "
            )
        )
        mList.add(
            HarfData(
                "E",
                "Endeks: Borsada işlem gören belirli bir grup hisse senedinin performansını ölçen bir göstergedir (örneğin, BIST 100)."
            )
        )
        mList.add(
            HarfData(
                "F",
                "Finansal Varlık: Bir kişinin veya kurumun sahip olduğu tüm para değeridir. Örneğin, birinin banka hesaplarındaki parası, hisse senetleri, tahviller, yatırım fonları ve emeklilik hesaplarındaki birikimler finansal varlıklara örnektir."
            )
        )
        mList.add(
            HarfData(
                "H",
                "Hazine Tahvili: Devlet tarafından uzun vadeli borçlanma için çıkarılan güvenli yatırım aracıdır. Örneğin, 10 yıl vadeli hazine tahvili aldığınızda, devlet size bu sürenin sonunda ana paranızı geri öder ve belirli aralıklarla faiz ödemesi yapar.\n" +
                        "Hisse Senedi: Bir şirkete ortak olmanızı sağlayan yatırım aracıdır. Örneğin, bir şirketin hisse senedini satın aldığınızda, o şirketin kârından pay alma ve şirketin değer kazancından yararlanma hakkınız olur."
            )
        )
        mList.add(
            HarfData(
                "I",
                " "
            )
        )
        mList.add(
            HarfData(
                "İ",
                " "
            )
        )
        mList.add(
            HarfData(
                "J",
                " "
            )
        )
        mList.add(
            HarfData(
                "K",
                "Kısa Pozisyon (Açığa Satış): Bir yatırımcının, değer kaybedeceğini düşündüğü varlığı ödünç alarak satması ve sonra daha düşük fiyattan geri alarak kazanç sağlamasıdır. Örneğin, bir hisse senedinin fiyatının düşeceğini düşünüyorsanız, bu hisseyi ödünç alıp satarsınız ve fiyat düştüğünde geri alıp iade ederek kâr edersiniz."
            )
        )
        mList.add(
            HarfData(
                "L",
                "Likidite: Bir varlığın hızlı ve kolay bir şekilde nakde çevrilebilme derecesidir."
            )
        )
        mList.add(
            HarfData(
                "M",
                " "
            )
        )
        mList.add(
            HarfData(
                "N",
                " "
            )
        )
        mList.add(
            HarfData(
                "O",
                " "
            )
        )
        mList.add(
            HarfData(
                "Ö",
            " "
            )
        )
        mList.add(
            HarfData(
                "P",
                "Piyasa Değeri: Bir şirketin toplam hisse senedi fiyatının toplam hisse adedi ile çarpılmasıyla elde edilen değerdir.\n" +
                        "Portföy: Bir kişinin veya kurumun sahip olduğu çeşitli yatırım araçlarının toplamıdır. Örneğin, bir portföyde hisse senetleri, tahviller ve yatırım fonları gibi çeşitli yatırımlar bulunabilir."
            )
        )
        mList.add(
            HarfData(
                "R",
                "Riskten Korunma (Hedge): Yatırımcıların, yatırım risklerini azaltmak için yaptıkları işlemlerdir. Örneğin, dövizde değer kaybı riskine karşı korunmak için dövizde satış pozisyonu açabilirsiniz. Böylece, döviz düşerse bu işlemden kazanç elde edersiniz ve zararınızı telafi edersiniz."
            )
        )
        mList.add(
            HarfData(
                "S",
                " "
            )
        )
        mList.add(
            HarfData(
                "Ş",
                " "
            )
        )
        mList.add(
            HarfData(
                "T",
                "Tahvil: Devletler veya şirketler tarafından borç almak için çıkarılan, belirli bir süre sonunda geri ödenen belgedir. Örneğin, bir şirket tahvil çıkarırsa, yatırımcı bu tahvili alarak şirkete borç verir ve karşılığında faiz geliri elde eder.\n" +
                        "Temettü: Şirketlerin kârlarının bir kısmını hisse senedi sahiplerine dağıtmasıdır."
            )
        )
        mList.add(
            HarfData(
                "U",
                "Uzun Pozisyon: Bir yatırımcının, değer kazanacağını düşündüğü varlığı satın almasıdır. Örneğin, bir hisse senedinin fiyatının yükseleceğini düşünüyorsanız, bu hisseyi satın alır ve fiyatı yükseldiğinde satarak kâr elde edersiniz."
            )
        )
        mList.add(
            HarfData(
                "Ü",
                " "
            )
        )
        mList.add(
            HarfData(
                "V",
                "Volatilite: Bir hisse senedinin fiyatının belirli bir zaman diliminde ne kadar değişkenlik gösterdiğini ifade eder. Örneğin, bir hisse senedinin fiyatı sık sık ve büyük ölçüde değişiyorsa, bu hisse senedi yüksek volatiliteye sahiptir ve bu da daha yüksek risk anlamına gelir."
            )
        )
        mList.add(
            HarfData(
                "Y",
                "Yatırım Fonu: Birçok yatırımcının parasını bir araya getirip, profesyonel bir yönetici tarafından yönetilen fondur. Örneğin, hisse senetleri ve tahvillerden oluşan bir yatırım fonu aldığınızda, riskinizi dağıtarak profesyonel yönetim avantajından faydalanırsınız.")
        )
        mList.add(
            HarfData(
                "Z",
                " "
            )
        )
    }

}