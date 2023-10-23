package com.example.ulkebayraklariquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var buttonOyunaBasla:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        buttonOyunaBasla = findViewById(R.id.buttonOyunaBasla)
        veritabaniKopyala()

        buttonOyunaBasla.setOnClickListener {

            startActivity(Intent(this@MainActivity,QuizSayfasiActivity::class.java))



        }



    }

    fun veritabaniKopyala() {
        val copyHelper = DatabaseCopyHelper(this)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()

        }catch (e:Exception) {
            e.printStackTrace()
        }
    }
}