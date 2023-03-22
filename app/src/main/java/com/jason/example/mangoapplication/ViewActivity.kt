package com.jason.example.mangoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class ViewActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)


        auth = Firebase.auth

        // intent로 main에서 받아오기

        intent.getStringExtra("url")

        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl(intent.getStringExtra("url").toString())

        // Write a message to the database
        val database = Firebase.database
        val myBookmark = database.getReference("Bookmark_ref")

        val url = intent.getStringExtra("url").toString()
        val title = intent.getStringExtra("title").toString()
        val imgUrl = intent.getStringExtra("imgUrl").toString()

        val saveText = findViewById<TextView>(R.id.saveText)
        saveText.setOnClickListener{
        myBookmark.child(auth.currentUser!!.uid).push().setValue(ContentsModel(url,imgUrl,title))
        }

    }
}