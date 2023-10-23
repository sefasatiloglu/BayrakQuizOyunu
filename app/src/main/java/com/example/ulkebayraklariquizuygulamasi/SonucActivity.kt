package com.example.ulkebayraklariquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SonucActivity : AppCompatActivity() {
    lateinit var textViewSonuc :TextView
    lateinit var textViewYuzdeSonuc:TextView
    lateinit var buttonTekrarDene:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sonuc)
        textViewSonuc = findViewById(R.id.textViewSonuc)
        textViewYuzdeSonuc = findViewById(R.id.textViewYuzdeSonuc)
        buttonTekrarDene = findViewById(R.id.buttonTekrarDene)

        val dogruSayac = intent.getIntExtra("dogruSayac",0)
        textViewSonuc.text = "$dogruSayac DOGRU ${5-dogruSayac} YANLIS"
        textViewYuzdeSonuc.text = "% ${(dogruSayac*100)/5} BASARI"

        buttonTekrarDene.setOnClickListener {

            startActivity(Intent(this@SonucActivity,QuizSayfasiActivity::class.java))
            finish()



        }




    }
}