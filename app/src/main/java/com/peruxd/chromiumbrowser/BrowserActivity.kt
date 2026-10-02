package com.peruxd.chromiumbrowser

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.peruxd.chromiumbrowser.databinding.ActivityBrowserBinding

class BrowserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrowserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.browserWebView.settings.javaScriptEnabled = true
        binding.browserWebView.settings.domStorageEnabled = true
        binding.browserWebView.settings.databaseEnabled = true
        binding.browserWebView.settings.allowFileAccess = true
        binding.browserWebView.webViewClient = WebViewClient()

        binding.goButton.setOnClickListener {
            val rawUrl = binding.urlInput.text.toString().trim()
            val url = normalizeUrl(rawUrl)
            binding.browserWebView.loadUrl(url)
        }

        binding.browserWebView.loadUrl("https://www.google.com")
    }

    private fun normalizeUrl(rawUrl: String): String {
        if (rawUrl.isBlank()) return "https://www.google.com"
        return if (rawUrl.startsWith("http://") || rawUrl.startsWith("https://")) {
            rawUrl
        } else {
            "https://$rawUrl"
        }
    }

    override fun onBackPressed() {
        if (binding.browserWebView.canGoBack()) {
            binding.browserWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (::binding.isInitialized) {
            binding.browserWebView.stopLoading()
            binding.browserWebView.destroy()
        }
        super.onDestroy()
    }
}
