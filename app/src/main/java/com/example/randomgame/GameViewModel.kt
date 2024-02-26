package com.example.randomgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


sealed class GameState{
    object InputRequest: GameState()
    object Win: GameState()
    object Greater: GameState()
    object Less: GameState()




}
class GameViewModel: ViewModel() {

    private var random = randomNumbers()
    var attempts = 0

    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState>
        get() = _gameState

    init {
        startNewGame()
    }
    private fun startNewGame(){
        random = randomNumbers()
        attempts = 0
        _gameState.value = GameState.InputRequest
    }

    fun checkUserInput(userInput: Int){
        attempts++
        if(userInput == random){
            _gameState.value = GameState.Win
        } else if (userInput > random){
            _gameState.value = GameState.Greater

        } else _gameState.value = GameState.Less


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