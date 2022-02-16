package com.example.readfilefromassets

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var byteArray: ByteArray
    private val charset = Charsets.UTF_8
    private val reg = Regex("(?=421C)")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)

        button.setOnClickListener {
            val data = readFromAsset()
            val noSpace = data.replace("\\s".toRegex(), "")
            val list = noSpace.split(reg)
            list.forEach {
                byteArray = it.toByteArray(charset)
                println("->" + byteArray.contentToString())
            }
        }
    }

    private fun readFromAsset(): String {
        val fileName = "inputFile.txt"
        val bufferReader = application.assets.open(fileName).bufferedReader()
        val result = bufferReader.use {
            it.readText()
        }
        return result
    }
}
