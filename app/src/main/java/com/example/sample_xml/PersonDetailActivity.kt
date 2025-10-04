package com.example.sample_xml

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class PersonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)

        val toolbar: Toolbar = findViewById(R.id.headerToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener { finish() }

        val name = intent.getStringExtra("name")
        val imageResId = intent.getIntExtra("imageResId", R.drawable.ic_person)
        val email = intent.getStringExtra("email")
        val mobile = intent.getStringExtra("mobile")

        val nameText: TextView = findViewById(R.id.detailName)
        val imageView: ImageView = findViewById(R.id.detailImage)
        val emailText: TextView = findViewById(R.id.detailEmail)
        val mobileText: TextView = findViewById(R.id.detailMobile)

        nameText.text = name
        imageView.setImageResource(imageResId)
        emailText.text = email
        mobileText.text = mobile
    }
}