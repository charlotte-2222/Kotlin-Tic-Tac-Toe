package com.example.charlotte_childers_cpt_188_final_project


class GameManager {
 //  var gameState: GameState = GameState.PLAYING
    private var currentPlayer = 1
    var player1Points = 0 // player 1's score
    var player2Points = 0 // player 2's score

    val currentPlayerMark: String
        get() {
            return if (currentPlayer == 1) "X" else "O"
        }

    private var state = arrayOf( // 2D Array
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    fun makeMove(position: Position): WinningLine? { // returns winning line if there is one, null otherwise
        state[position.row][position.column] = currentPlayer // set the position to the current player
        val winningLine = hasGameEnded() // check if there is a winning line

        if (winningLine == null) {
            currentPlayer = 3 - currentPlayer
        } else {
            when (currentPlayer) { // Switch to next player
                1 -> player1Points++
                2 -> player2Points++
            }
        }

        return winningLine
    }

    fun reset() { // Reset the game
        state = arrayOf( // 2D Array
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        currentPlayer = 1 // Reset the current player to player 1
    }

    private fun hasGameEnded(): WinningLine? { // Returns null if no winner, otherwise returns the winning line
        if (state[0][0] == currentPlayer && state[0][1] == currentPlayer && state[0][2] == currentPlayer) {
            return WinningLine.ROW_0
        } else if (state[1][0] == currentPlayer && state[1][1] == currentPlayer && state[1][2] == currentPlayer) {
            return WinningLine.ROW_1
        } else if (state[2][0] == currentPlayer && state[2][1] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.ROW_2
        } else if (state[0][0] == currentPlayer && state[1][0] == currentPlayer && state[2][0] == currentPlayer) {
            return WinningLine.COLUMN_0
        } else if (state[0][1] == currentPlayer && state[1][1] == currentPlayer && state[2][1] == currentPlayer) {
            return WinningLine.COLUMN_1
        } else if (state[0][2] == currentPlayer && state[1][2] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.COLUMN_2
        } else if (state[0][0] == currentPlayer && state[1][1] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.DIAGONAL_LEFT
        } else if (state[0][2] == currentPlayer && state[1][1] == currentPlayer && state[2][0] == currentPlayer) {
            return WinningLine.DIAGONAL_RIGHT
        }
        return null
    }

    private fun hasGameEndedV2(): Boolean { // Returns true if no winner, otherwise returns false
        state.forEach { row ->
            val hasWon = row.all { player -> player == currentPlayer }
            if (hasWon) return true
        }

        for (i: Int in state.indices) {
            val hasWon = state[i].all { player -> player == currentPlayer }
            if (hasWon) return true
        }

        for (i in state.indices) { // Check diagonals
            if (state[i][i] != currentPlayer) return false
        }
        for (i in state.size until 0) {
            if (state[i][i] != currentPlayer) return false
        }

        return true
    }

    fun isDraw(): Boolean { // Returns true if there is a draw, false otherwise
        return state.all { row -> row.all { player -> player != 0 } } // Check if all positions are filled
    }


}