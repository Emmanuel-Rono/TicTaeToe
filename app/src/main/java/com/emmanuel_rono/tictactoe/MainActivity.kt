package com.emmanuel_rono.tictactoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val board = Array(3) { Array(3) { 0 } }
    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateBoardUI()

        // Set click listeners for each cell on the board
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val cell = findViewById<TextView>(resources.getIdentifier("cell_$i$j", "id", packageName))
                cell.setOnClickListener {
                    if (board[i][j] == 0) {
                        board[i][j] = 1
                        updateBoardUI()
                        checkWin()
                    }
                }
            }
        }
    }
    // Update the UI to reflect the current state of the game board
    @SuppressLint("DiscouragedApi")
    private fun updateBoardUI() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val cell = findViewById<TextView>(resources.getIdentifier("cell_$i$j", "id", packageName))
                when (board[i][j]) {
                   0 -> cell.text = ""
                    1 -> cell.text = "o"
                    2-> cell.text = "x"
                }
            }
        }
    }
    private fun checkWin() {
        // Check rows for a win
        for (i in 0 until 3) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                declareWinner(board[i][0])
                return
            }
        }
        // Check columns for a win
        for (j in 0 until 3) {
            if (board[0][j] != 0 && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                declareWinner(board[0][j])
                return
            }
        }

        // Check diagonals for a win
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            declareWinner(board[0][0])
            return
        }

        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            declareWinner(board[0][2])
            return
        }

        // Check for a tie
        var isTie = true
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board[i][j] == 0) {
                    isTie = false
                    break
                }
            }
        }
        if (isTie) {
            declareWinner(0)
        }
    }
    private fun declareWinner(player: Int) {
        val message = when (player) {
            0 -> "It's a tie!"
            1 -> "Player O wins!"
            2 -> "Player X wins!"
            else -> ""
        }
        val winnerTextView = findViewById<TextView>(R.id.winner_text_view)
        winnerTextView.text = message
        winnerTextView.visibility = TextView.VISIBLE

        // Disable all cells on the board
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val cell = findViewById<TextView>(resources.getIdentifier("cell_$i$j", "id", packageName))
                cell.isEnabled = false
            }
        }
    }

}

