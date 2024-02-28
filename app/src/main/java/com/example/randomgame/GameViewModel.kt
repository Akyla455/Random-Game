package com.example.randomgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


sealed class GameState{
    data class InputRequest(val resource: Int): GameState()
    data class Win(val resource: Int): GameState()
    data class Game(val resource: Int, val attempts: Int ): GameState()

}





class GameViewModel: ViewModel() {

    private var random = randomNumbers()
    private var getAttempts = 0

    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState>
        get() = _gameState

    init {
        startNewGame()
    }
    private fun startNewGame(){
        random = randomNumbers()
        getAttempts = 0
        _gameState.value = GameState.InputRequest(R.string.input_request)
    }

    fun checkUserInput(userInput: Int){
        getAttempts++
        if(userInput == random){
            _gameState.value = GameState.Win(R.string.request)

        } else if (userInput > random){
            _gameState.value = GameState.Game(R.string.hint1,getAttempts)

        } else {
            _gameState.value = GameState.Game(R.string.hint2, getAttempts)
        }


    }

    fun restartTheGame(){
        startNewGame()
    }

    private fun randomNumbers(): Int {
        val min = 1
        val max = 10
        return (min..max).random()
    }



}