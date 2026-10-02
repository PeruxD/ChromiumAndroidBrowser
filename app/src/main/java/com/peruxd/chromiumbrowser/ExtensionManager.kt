package com.peruxd.chromiumbrowser

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.nio.charset.StandardCharsets

data class ExtensionDescriptor(
    val id: String,
    val name: String,
    val version: String,
    val description: String,
    val path: String
)

class ExtensionManager(private val context: Context) {

    fun discoverExtensions(): List<ExtensionDescriptor> {
        val installed = mutableListOf<ExtensionDescriptor>()

        val folders = context.assets.list("extensions") ?: emptyArray()
        for (folder in folders) {
            if (folder.isBlank()) continue

            val manifestPath = "extensions/$folder/manifest.json"
            try {
                context.assets.open(manifestPath).use { inputStream ->
                    val json = JSONObject(inputStream.readBytes().toString(StandardCharsets.UTF_8))
                    val id = json.optString("name", folder)
                    val name = json.optString("name", folder)
                    val version = json.optString("version", "1.0.0")
                    val description = json.optString("description", "No description")

                    installed.add(
                        ExtensionDescriptor(
                            id = id,
                            name = name,
                            version = version,
                            description = description,
                            path = manifestPath
                        )
                    )
                }
            } catch (_: Exception) {
                // Ignorar extensiones con manifiesto inválido
            }
        }

        return installed
    }

    fun installedDir(): File {
        return File(context.filesDir, "extensions")
    }
}
