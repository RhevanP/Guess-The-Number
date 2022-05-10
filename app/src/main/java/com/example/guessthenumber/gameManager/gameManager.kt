package com.example.guessthenumber.gameManager

import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class gameManager {

    fun winOrLoose(winTag: Int,
                   buttonGuess: Button,
                   lifesLeft: Int,
                   hinText: TextView,
                   animWin: Animation,
                   animWrong: Animation,
                   animLost: Animation
    ){
        if (winTag==1){
            buttonGuess.setText("You found the answer!")
            buttonGuess.startAnimation(animWin)
        }
        else if (lifesLeft > 1){
            buttonGuess.setText("${lifesLeft-1} tries left")
            buttonGuess.startAnimation(animWrong)
        }
        else{
            buttonGuess.setText("You lost")
            hinText.setText("You ran out of lives")
            buttonGuess.startAnimation(animLost)
        }
    }

}