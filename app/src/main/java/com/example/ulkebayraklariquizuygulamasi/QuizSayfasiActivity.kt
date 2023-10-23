package com.example.ulkebayraklariquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class QuizSayfasiActivity : AppCompatActivity() {
    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenek:ArrayList<Bayraklar>
    private lateinit var dogruSoru:Bayraklar
    private lateinit var tumSecenekler:HashSet<Bayraklar>
    private lateinit var vt:VeritabanYardimcisi

    private  var soruSayac = 0
    private  var dogruSayac = 0
    private  var yanlisSayac = 0


    lateinit var buttonA:Button
    lateinit var buttonB:Button
    lateinit var buttonC:Button
    lateinit var buttonD:Button
    lateinit var textViewDogruSayisi :TextView
    lateinit var textViewYanlisSayisi:TextView
    lateinit var textViewSoruSayisi:TextView
    lateinit var imageViewBayrak:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_sayfasi)

        buttonA = findViewById(R.id.buttonA)
        buttonB = findViewById(R.id.buttonB)
        buttonC = findViewById(R.id.buttonC)
        buttonD = findViewById(R.id.buttonD)
        textViewDogruSayisi = findViewById(R.id.textViewDogruSayisi)
        textViewYanlisSayisi = findViewById(R.id.textViewYanlisSayisi)
        textViewSoruSayisi = findViewById(R.id.textViewSoruSayisi)
        imageViewBayrak = findViewById(R.id.imageViewBayrak)

        vt = VeritabanYardimcisi(this)

        val bdao = Bayraklardao()

        sorular = bdao.rastgelebesbayrakGetir(vt)

        soruYukle()

        buttonA.setOnClickListener {
            dogruKontrol(buttonA)
            soruSayacKontrol()

        }
        buttonB.setOnClickListener {
            dogruKontrol(buttonB)
            soruSayacKontrol()

        }
        buttonC.setOnClickListener {
            dogruKontrol(buttonC)
            soruSayacKontrol()

        }
        buttonD.setOnClickListener {
            dogruKontrol(buttonD)
            soruSayacKontrol()

        }


    }
    fun soruYukle() {
        textViewSoruSayisi.text = "${soruSayac+1}. Soru"
        dogruSoru = sorular.get(soruSayac)
        imageViewBayrak.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))

        yanlisSecenek = Bayraklardao().rastgeleucyanlisbayrakGetir(vt,dogruSoru.bayrak_id)
        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenek.get(0))
        tumSecenekler.add(yanlisSecenek.get(1))
        tumSecenekler.add(yanlisSecenek.get(2))
        buttonA.text = tumSecenekler.elementAt(0).bayrak_ad
        buttonB.text = tumSecenekler.elementAt(1).bayrak_ad
        buttonC.text = tumSecenekler.elementAt(2).bayrak_ad
        buttonD.text = tumSecenekler.elementAt(3).bayrak_ad



    }
    fun soruSayacKontrol() {
        soruSayac++

        if (soruSayac !=5) {
            soruYukle()
        }else {
            val intent = Intent(this@QuizSayfasiActivity,SonucActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)
            finish()

        }
    }
    fun dogruKontrol(button: Button) {
        val buttonYazi = button.text.toString()
        val dogruCevap = dogruSoru.bayrak_ad

        if (buttonYazi == dogruCevap) {
            dogruSayac++
        }else {
            yanlisSayac++
        }
        textViewDogruSayisi.text = "Dogru : $dogruSayac"
        textViewYanlisSayisi.text = "Yanlis : $yanlisSayac"
    }
}