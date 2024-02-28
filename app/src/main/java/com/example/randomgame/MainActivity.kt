package com.example.randomgame

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.randomgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupViews()
        observerGameState()

    }

    private fun setupViews(){
        viewBinding.buttonCheckingInput.setOnClickListener {
            val userInput = viewBinding.userInputField.text.toString().toIntOrNull()
            if(userInput != null){
                viewModel.checkUserInput(userInput)

            } else showToast("Введите корректное значение")
        }
        viewBinding.buttonRestartGame.setOnClickListener {
            viewModel.restartTheGame()
            showNewGame()
        }

        viewBinding.buttonEndGame.setOnClickListener {
            finish()
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



    private fun observerGameState(){
        viewModel.gameState.observe(this, Observer { gameState ->
            viewBinding.userInputField.text = null
            when(gameState){
                is GameState.InputRequest -> {
                    viewBinding.tvTitle.setText(gameState.resource)
                }

                is GameState.Win -> {
                    viewBinding.tvTitle.setText(gameState.resource)
                    viewBinding.hintField.text = null
                    viewBinding.attemptsField.text = null
                    showWinState()
                }

                is GameState.Game ->{
                    viewBinding.hintField.setText(gameState.resource)
                    viewBinding.attemptsField.text = gameState.attempts.toString()
                }
            }
        })
    }
    private fun showWinState() {
        viewBinding.group1.visibility = View.GONE
        viewBinding.group2.visibility = View.VISIBLE
    }

    private fun showNewGame(){
        viewBinding.group1.visibility = View.VISIBLE
        viewBinding.group2.visibility = View.GONE
    }
}