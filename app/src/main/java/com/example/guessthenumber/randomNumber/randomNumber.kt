package com.example.guessthenumber.randomNumber

class randomNumber { //Take from 1 to 100 range and pick randomly one with the CreateGuess function
    val potentialNumbers = 1..10
    var solution = 0
    fun createGuess(){
//        solution = 5 // For testing only
        solution = potentialNumbers.random()
    }
}