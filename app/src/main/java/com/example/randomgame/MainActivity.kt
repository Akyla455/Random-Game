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

        viewBinding.bt2.setOnClickListener {
            viewModel.restartTheGame()
            updateAttempts()
            invisible()

        }

        viewBinding.bt3.setOnClickListener {
            finish()
        }




    }




    private fun setupViews(){
        updateAttempts()
        viewBinding.bt1.setOnClickListener {
            val userInput = viewBinding.edText.text.toString().toIntOrNull()
            if(userInput != null){
                viewModel.checkUserInput(userInput)
                updateAttempts()
            } else showToast("Введите корректное значение")
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun updateAttempts(){
        viewBinding.tvQuantity2.text = viewModel.attempts.toString()

    }


    private fun observerGameState(){
        viewModel.gameState.observe(this, Observer { gameState ->
            when(gameState){
                is GameState.InputRequest -> {
                    viewBinding.tvTitle.setText(R.string.input_request)
                    viewBinding.edText.text = null
                }
                is GameState.Win -> {
                    viewBinding.tvTitle.setText(R.string.request)
                    viewBinding.edText.text = null
                    viewBinding.tvHint2.text = null
                    visibility()
                }
                is GameState.Greater ->{
                    viewBinding.tvHint2.setText(R.string.hint1)
                    viewBinding.edText.text = null
                }
                is GameState.Less -> {
                    viewBinding.tvHint2.setText(R.string.hint2)
                    viewBinding.edText.text = null
                }

            }

        })
    }









    private fun visibility() {
        viewBinding.group1.visibility = View.GONE
        viewBinding.group2.visibility = View.VISIBLE
    }

    private fun invisible(){
        viewBinding.group1.visibility = View.VISIBLE
        viewBinding.group2.visibility = View.GONE
        //viewBinding.tvTitle.setText(R.string.input_request)


    }



}






