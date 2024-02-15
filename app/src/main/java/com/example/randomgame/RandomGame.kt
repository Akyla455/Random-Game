package com.example.randomgame

import android.content.Context
import com.example.randomgame.databinding.ActivityMainBinding

class RandomGame(private val binding: ActivityMainBinding, private val context: Context) {

    private fun generateRandomNumber(min: Int, max: Int): Int {
        return (min..max).random()
    }

    private fun checkUserInput(randomNumber: Int, userInput: Int): Boolean {
        if (userInput != randomNumber) {
            if (userInput > randomNumber) {
                binding.tv1.text = context.getString(R.string.error1)
            } else {
                binding.tv1.text = context.getString(R.string.error2)
            }
            return false
        } else {
            binding.tv1.text = context.getString(R.string.offset)
            return true
        }
    }

    private fun readUserInput(): Int? {
        val edText = binding.edText.text.toString()
        return edText.toIntOrNull()
    }

    private fun askToPlayAgain() {
        binding.tv1.text = context.getString(R.string.request)
        var userInput = binding.edText.text.toString()

        while (userInput != "да" && userInput != "нет") {
            binding.tv1.text = context.getString(R.string.error3)
            userInput = binding.edText.text.toString()
        }

        if (userInput == "да") {
            randomGame()
        } else {
            binding.tv1.text = context.getString(R.string.thanks)
        }
    }

    fun randomGame() {
        val min = 1
        val max = 10

        val randomNumber = generateRandomNumber(min, max)
        var result = false

        while (!result) {
            val userInput = readUserInput()
            if (userInput != null) {
                result = checkUserInput(randomNumber, userInput)
            }
        }
        askToPlayAgain()
    }
}