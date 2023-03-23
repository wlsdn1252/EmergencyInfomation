package com.example.emergencyinfomation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goInputActivityButton.setOnClickListener{
            val intent = Intent(this, InputActivity :: class.java)
            intent.putExtra("intentMessag","응급의료정보")
            startActivity(intent)
        }
    }
}