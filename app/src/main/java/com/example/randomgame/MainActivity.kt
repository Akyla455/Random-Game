package com.example.randomgame
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast

import com.example.randomgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private var random = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)



        random = randomNumbers()

        viewBinding.bt1.setOnClickListener {
            game()
        }

        viewBinding.bt2.setOnClickListener {
            random = randomNumbers()
            invisible()
            updateAttempts()

        }
        viewBinding.bt3.setOnClickListener {
            stopGame()
        }

    }

    private fun randomNumbers(): Int {
        val min = 1
        val max = 10
        return (min..max).random()
    }

    private fun readUserInputNumber(): Int? {
        val userNumber = viewBinding.edText.text.toString()
        return userNumber.toIntOrNull()
    }



    private fun stopGame() {
        viewBinding.tvTitle.setText(R.string.thanks)
        handler.postDelayed({
            finish()
        }, 3000)
    }

    private fun visibility() {
        viewBinding.bt1.visibility = View.GONE
        viewBinding.edText.visibility = View.GONE
        viewBinding.tvHint.visibility = View.GONE
        viewBinding.tvHint2.visibility = View.GONE
        viewBinding.tvQuantity.visibility = View.GONE
        viewBinding.tvQuantity2.visibility = View.GONE
        viewBinding.bt2.visibility = View.VISIBLE
        viewBinding.bt3.visibility =View.VISIBLE
    }

    private fun invisible(){
        viewBinding.bt1.visibility = View.VISIBLE
        viewBinding.edText.visibility = View.VISIBLE
        viewBinding.tvHint.visibility = View.VISIBLE
        viewBinding.tvHint2.visibility = View.VISIBLE
        viewBinding.tvQuantity.visibility = View.VISIBLE
        viewBinding.tvQuantity2.visibility = View.VISIBLE
        viewBinding.edText.visibility = View.VISIBLE
        viewBinding.bt2.visibility = View.GONE
        viewBinding.bt3.visibility = View.GONE
        viewBinding.tvTitle.setText(R.string.input_request)
        attempts = 0

    }

    private fun updateAttempts(){
        viewBinding.tvQuantity2.text = getString(R.string.attempts_format).format(attempts)
    }

    private fun game() {
        val userInput = readUserInputNumber()
        attempts++
        updateAttempts()
        if (userInput != null) {
            if (userInput == random) {
                viewBinding.tvHint2.text = null
                viewBinding.edText.text = null
                viewBinding.tvTitle.setText(R.string.request)
                visibility()
            } else {
                if (userInput > random) {
                    viewBinding.edText.text = null
                    viewBinding.tvHint2.text = getString(R.string.hint1,).format(userInput)
                } else {
                    viewBinding.edText.text = null
                    viewBinding.tvHint2.text = getString(R.string.hint2).format(userInput)
                }
            }
        } else {
             Toast.makeText(this, "Введите корректное число", Toast.LENGTH_SHORT).show()
        }

    }
}






