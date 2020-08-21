package com.rulyox.googlenewsrss.activity.read

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.rulyox.googlenewsrss.R
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity: AppCompatActivity() {

    private var title: String? = null
    private var link:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        setSupportActionBar(read_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initIntent()
        initUI()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && read_web.canGoBack()) {
            read_web.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)

    }

    private fun initIntent() {

        title = intent.getStringExtra("title")
        link = intent.getStringExtra("link")

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initUI() {

        read_title.text = title

        read_web.webViewClient = WebViewClient()
        read_web.webChromeClient = WebChromeClient()
        read_web.settings.javaScriptEnabled = true
        read_web.loadUrl(intent.getStringExtra("link"))

    }

}
