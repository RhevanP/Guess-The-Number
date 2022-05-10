package com.example.guessthenumber

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.guessthenumber.buttonClicked.buttonClicked
import com.example.guessthenumber.buttonClicked.historyOfGuesses
import com.example.guessthenumber.gameManager.gameManager
import com.example.guessthenumber.randomNumber.randomNumber
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this){}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mAdView.adListener = object: AdListener(){
            override fun onAdLoaded() {
                Log.v("AGP", "The Refresh worked")
            }
        }

        val buttonGuess: Button = findViewById(R.id.validateguess) //So we detect when the user is ready to guess
        val buttonReset: Button = findViewById(R.id.buttonReset)
        var userGuess: TextView = findViewById<TextView>(R.id.guess)
        var hintText: TextView = findViewById(R.id.text_hint)
        val historyOfGuessesText: TextView = findViewById(R.id.historyOfGuesses)

        val randomNumberIterated: randomNumber = randomNumber() //Instantiate the class
        val gameManagerMain: gameManager = gameManager()
        val historyOfGuesses: historyOfGuesses = historyOfGuesses(historyOfGuessesText)

        var winTag = 0
        var lifesLeft = 3

        val winTagEndOfGameWin = 1
        val lifesLosts = 1
        val lifeStart = 3
        val winTagMissedGuess = -1

        val animWin: Animation = AnimationUtils.loadAnimation(this, R.anim.animation_win)
        val animWrong: Animation = AnimationUtils.loadAnimation(this, R.anim.animation_wrong)
        val animLost: Animation = AnimationUtils.loadAnimation(this, R.anim.animation_lost)

        randomNumberIterated.createGuess() // Create a new guess

        fun View.hideKeyboard(){
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

        buttonGuess.setOnClickListener {
            if(userGuess.length() < 1){
                buttonGuess.startAnimation(animWrong)
                hintText.setText(R.string.typed_no_numbers)
                winTag = 0
            }

            else if (winTag != winTagEndOfGameWin && lifesLeft > 0 && userGuess.length()>=1) { //So we do something only if we haven't won already!
                winTag = buttonClicked.buttonClicked(
                    randomNumberIterated.solution,
                    gameManagerMain,
                    buttonGuess,
                    randomNumberIterated,
                    lifesLeft,
                    userGuess,
                    hintText,
                    historyOfGuesses,
                    animWin,
                    animWrong,
                    animLost
                ) //To compare the solution to the input
            }
            else if(winTag == winTagEndOfGameWin){
                buttonGuess.startAnimation(animWin)
            }

            when(winTag){
                winTagMissedGuess -> lifesLeft -= lifesLosts
                else -> lifesLeft = lifesLeft
            }

            //Reset the state
            userGuess.setText("")
            userGuess.setHint(R.string.type_your_guess)
            buttonGuess.hideKeyboard()
        }


        buttonReset.setOnClickListener {
            // Reset the state of everything
            lifesLeft = lifeStart
            winTag = 0
            hintText.setText(R.string.what_can_it_be)
            buttonGuess.setText(R.string.button)
            userGuess.setText("")
            userGuess.setHint(R.string.type_your_guess)
            historyOfGuesses.resetHistory()
            historyOfGuessesText.setText(R.string.history_of_guesses)
            randomNumberIterated.createGuess()
            buttonGuess.clearAnimation() // To avoid any weird bug if the animation is still playing
            buttonReset.hideKeyboard() // So we make sure there's no more keyboard issue there
        }
    }
    }
