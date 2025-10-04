package com.example.sample_xml

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.headerToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchInput = findViewById<EditText>(R.id.searchInput)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val people = listOf(
            Person("Anna Müller", "email@telekom.de", "012345678", R.drawable.ic_person),
            Person("Max Mustermann", "email@telekom.de", "012345678", R.drawable.ic_person),
            Person("Beate Beispiel", "email@telekom.de", "012345678", R.drawable.ic_person),
            Person("Max Liebig", "email@telekom.de", "012345678", R.drawable.ic_person),
            Person("Annika Zehne", "email@telekom.de", "012345678", R.drawable.ic_person),
            Person("Tom Schneider", "email@telekom.de", "012345678", R.drawable.ic_person),
            Person("Lisa Becker", "email@telekom.de", "012345678", R.drawable.ic_person)
        )

        val adapter = PersonAdapter(people)
        recyclerView.adapter = adapter

        searchInput.addTextChangedListener { query ->
            adapter.filter.filter(query)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            currentFocus!!.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }
}