package com.android.example.thaichana

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web.*

class Web : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get URL from scanned qrcode
        val placeURL = intent.getStringExtra("key")


        //open webview
        webthaichanaview.loadUrl(placeURL)
        Toast.makeText(this,"Loading please wait",Toast.LENGTH_LONG).show()

        //setting
        webthaichanaview.settings.javaScriptEnabled = true
        webthaichanaview.settings.allowContentAccess = true
        webthaichanaview.settings.domStorageEnabled = true
        webthaichanaview.settings.useWideViewPort = true
        webthaichanaview.settings.setAppCacheEnabled(true)
    }
}
