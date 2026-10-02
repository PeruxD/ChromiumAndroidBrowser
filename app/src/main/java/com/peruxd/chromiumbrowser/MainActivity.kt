package com.peruxd.chromiumbrowser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peruxd.chromiumbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extensions = ExtensionManager(this).discoverExtensions()

        binding.appTitle.text = getString(R.string.app_name)
        binding.extensionCounter.text = getString(
            R.string.extensions_count,
            extensions.size
        )

        binding.extensionList.text = if (extensions.isEmpty()) {
            getString(R.string.no_extensions)
        } else {
            extensions.joinToString(separator = "\n") { "• ${it.name} v${it.version}" }
        }

        binding.openBrowserButton.setOnClickListener {
            startActivity(Intent(this, BrowserActivity::class.java))
        }
    }
}
