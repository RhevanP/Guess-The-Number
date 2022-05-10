package com.example.guessthenumber.buttonClicked

import android.widget.TextView

class historyOfGuesses(displayedHistoryofGuesses: TextView) {
    var historyUserGuesses = mutableListOf<Int>()
    var historyUserComparison = mutableListOf<String>()
    var displayedHistoryofGuesses = displayedHistoryofGuesses

    fun addingToHistory(comparisonWithSolution:String, userGuess:Int){
        historyUserGuesses.add(userGuess)
        historyUserComparison.add(comparisonWithSolution + userGuess.toString())
        updatingHistory(historyUserComparison)
    }

    fun resetHistory(){
        historyUserGuesses.clear()
        historyUserComparison.clear()
        updatingHistory(historyUserComparison)
    }

    fun updatingHistory(historyUserComparison: MutableList<String>){
        displayedHistoryofGuesses.setText(historyUserComparison.joinToString(" "))
    }
}
