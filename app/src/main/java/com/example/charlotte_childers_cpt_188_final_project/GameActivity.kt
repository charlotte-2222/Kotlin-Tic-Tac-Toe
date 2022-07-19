package com.example.charlotte_childers_cpt_188_final_project

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class GameActivity : AppCompatActivity() {
    // Declare variables
    private lateinit var gameManager: GameManager
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var startNewGameButton: Button
    private lateinit var player1Points: TextView
    private lateinit var player2Points: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameManager = GameManager() // Initialize gameManager
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        startNewGameButton = findViewById(R.id.start_new_game_button)
        player1Points = findViewById(R.id.player_one_score)
        player2Points = findViewById(R.id.player_two_score)
       // endGame = findViewById(R.id.end_game_button) not a good idea apparently
        one.setOnClickListener { onBoxClicked(one, Position(0, 0)) }
        two.setOnClickListener { onBoxClicked(two, Position(0, 1)) }
        three.setOnClickListener { onBoxClicked(three, Position(0, 2)) }
        four.setOnClickListener { onBoxClicked(four, Position(1, 0)) }
        five.setOnClickListener { onBoxClicked(five, Position(1, 1)) }
        six.setOnClickListener { onBoxClicked(six, Position(1, 2)) }
        seven.setOnClickListener { onBoxClicked(seven, Position(2, 0)) }
        eight.setOnClickListener { onBoxClicked(eight, Position(2, 1)) }
        nine.setOnClickListener { onBoxClicked(nine, Position(2, 2)) }

        startNewGameButton.setOnClickListener {
            startNewGameButton.visibility = View.GONE
            gameManager.reset()
            resetboxes()
        }


        updatePoints() // Update points on start of game (player 1 starts) and update the boxes
    }

    @SuppressLint("SetTextI18n") // Suppress lint error for setting text to a string with a number
    private fun updatePoints() {
        player1Points.text = "Player X Points: ${gameManager.player1Points}" // Update player 1 points
        player2Points.text = "Player O Points: ${gameManager.player2Points}" // Update player 2 points
    } // End of updatePoints()


    private fun resetboxes() { // Reset the boxes to their original state (empty) and update the points
        one.text = ""
        two.text = ""
        three.text = ""
        four.text = ""
        five.text = ""
        six.text = ""
        seven.text = ""
        eight.text = ""
        nine.text = ""
        one.background = null
        two.background = null
        three.background = null
        four.background = null
        five.background = null
        six.background = null
        seven.background = null
        eight.background = null
        nine.background = null
        one.isEnabled = true
        two.isEnabled = true
        three.isEnabled = true
        four.isEnabled = true
        five.isEnabled = true
        six.isEnabled = true
        seven.isEnabled = true
        eight.isEnabled = true
        nine.isEnabled = true
    }

    private fun onBoxClicked(box: TextView, position: Position) { // When a box is clicked, check if the box is empty and if it is, update the box with the player's symbol and disable the box
        if (box.text.isEmpty()) {
            box.text = gameManager.currentPlayerMark
            val winningLine = gameManager.makeMove(position)
            if (winningLine != null) {
                updatePoints()
                disableBoxes()
                startNewGameButton.visibility = View.VISIBLE
                showWinner(winningLine)
                Toast.makeText(this, "Player ${gameManager.currentPlayerMark} wins!", Toast.LENGTH_LONG).show()
                // If the game is a draw, show a message and disable the boxes
            }
            else if(gameManager.isDraw()) { // If the game is a draw, show a message and disable the boxes
                updatePoints() // Update the points
                disableBoxes() // Disable the boxes
                startNewGameButton.visibility = View.VISIBLE // Show the start new game button
                Toast.makeText(this, "It's a draw!", Toast.LENGTH_LONG).show() // Show a message that the game is a draw
            }

        }
    }

    private fun disableBoxes() {
        // Disable all boxes and update the points (player 2 starts)
        one.isEnabled = false
        two.isEnabled = false
        three.isEnabled = false
        four.isEnabled = false
        five.isEnabled = false
        six.isEnabled = false
        seven.isEnabled = false
        eight.isEnabled = false
        nine.isEnabled = false
    }

    private fun showWinner(winningLine: WinningLine) {
        val (winningBoxes, background) = when (winningLine) {
            // If the winning line is a horizontal line, set the background of the winning boxes to the winning color and disable the boxes
                // If the winning line is a vertical line, set the background of the winning boxes to the winning color and disable the boxes
                    // If the winning line is a diagonal line, set the background of the winning boxes to the winning color and disable the boxes
            WinningLine.ROW_0 -> Pair(listOf(one, two, three), R.drawable.horizontal_line)
            WinningLine.ROW_1 -> Pair(listOf(four, five, six), R.drawable.horizontal_line)
            WinningLine.ROW_2 -> Pair(listOf(seven, eight, nine), R.drawable.horizontal_line)
            WinningLine.COLUMN_0 -> Pair(listOf(one, four, seven), R.drawable.vertical_line)
            WinningLine.COLUMN_1 -> Pair(listOf(two, five, eight), R.drawable.vertical_line)
            WinningLine.COLUMN_2 -> Pair(listOf(three, six, nine), R.drawable.vertical_line)
            WinningLine.DIAGONAL_LEFT -> Pair(listOf(one, five, nine),
                R.drawable.left_diagonal_line
            )
            WinningLine.DIAGONAL_RIGHT -> Pair(listOf(three, five, seven),
                R.drawable.right_diagonal_line
            )
        }

        winningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(this, background)
            // Set the background of the winning boxes to the winning color and disable the boxes
        }
    }

}