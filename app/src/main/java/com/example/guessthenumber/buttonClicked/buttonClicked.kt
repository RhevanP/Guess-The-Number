package com.example.guessthenumber.buttonClicked

import android.app.Activity
import android.content.Context
import android.hardware.input.InputManager
import android.provider.Settings.Global.getString
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.guessthenumber.R
import com.example.guessthenumber.gameManager.gameManager
import com.example.guessthenumber.randomNumber.randomNumber

class buttonClicked {
    companion object {
        fun buttonClicked(
            solution: Int,
            gameManagerMain: gameManager,
            buttonGuess: Button,
            randomNumberIterated: randomNumber,
            lifesLeft: Int,
            userGuess: TextView,
            hintText: TextView,
            historyOfGuesses: historyOfGuesses,
            animWin: Animation,
            animWrong: Animation,
            animLost: Animation
        ): Int {

        var hintTextToDisplay = ""
        var winTag = 0
            val userGuessNumber = Integer.parseInt(userGuess.text.toString())

            if (userGuessNumber > 10) {
                hintText.setText(R.string.guess_is_above_10)
                buttonGuess.setText("The value is between " + randomNumberIterated.potentialNumbers.first + " and " + randomNumberIterated.potentialNumbers.last)
                return winTag
            } else if (userGuessNumber == 0) {
                hintText.setText(R.string.guess_is_0)
                buttonGuess.setText("The value cannot be 0")
                return winTag
            } else if (solution > userGuessNumber) {
                hintText.setText(R.string.solution_is_higher)
                historyOfGuesses.addingToHistory(">", userGuessNumber)
                winTag = -1
                gameManagerMain.winOrLoose(
                    winTag,
                    buttonGuess,
                    lifesLeft,
                    hintText,
                    animWin,
                    animWrong,
                    animLost
                )
                return winTag
            } else if (solution < userGuessNumber) {
                hintText.setText(R.string.solution_is_lower)
                historyOfGuesses.addingToHistory("<", userGuessNumber)
                winTag = -1
                gameManagerMain.winOrLoose(
                    winTag,
                    buttonGuess,
                    lifesLeft,
                    hintText,
                    animWin,
                    animWrong,
                    animLost
                )
                return winTag
            } else {
                hintText.setText(R.string.solution_found)
                historyOfGuesses.addingToHistory("=", userGuessNumber)
                winTag = 1
                gameManagerMain.winOrLoose(
                    winTag,
                    buttonGuess,
                    lifesLeft,
                    hintText,
                    animWin,
                    animWrong,
                    animLost
                )
                return winTag
            }
        }
    }
}