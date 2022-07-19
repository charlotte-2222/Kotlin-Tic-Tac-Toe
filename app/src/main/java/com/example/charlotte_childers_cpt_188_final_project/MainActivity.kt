package com.example.charlotte_childers_cpt_188_final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var startNewGameButton: Button
    // lateinit var loadGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // set up the buttons and listeners for the main menu screen

        startNewGameButton = findViewById(R.id.startNewGameButton) // set the button to the startNewGameButton

        startNewGameButton.setOnClickListener {
            val intent = Intent(MainActivity@this, GameActivity::class.java)
            startActivity(intent) // start the game activity
        }
    }
}