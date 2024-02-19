package com.example.randomgame
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast

import com.example.randomgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private var random = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)



        random = randomNumbers()

        viewBinding.bt1.setOnClickListener {
            game()
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
        handler.postDelayed({
            finish()
        }, 3000)
    }

    private fun askAgain() {
        viewBinding.tvTitle.setText(R.string.request)
        when (readUserInputNumber().toString()) {
            "да" -> {
                viewBinding.tvTitle.setText(R.string.input_request)
                game()
            }
            "нет" -> {
                viewBinding.tvTitle.setText(R.string.thanks)
                stopGame()
            }
        }
    }

    private fun game() {
        val userInput = readUserInputNumber()
        if (userInput != null) {
            if (userInput == random) {
                viewBinding.tvHint2.text = null
                viewBinding.tvTitle.setText(R.string.offset)
                askAgain()

            } else {
                if (userInput > random) {
                    viewBinding.tvHint2.setText(R.string.hint1)
                } else {
                    viewBinding.tvHint2.setText(R.string.hint2)
                }
            }
        } else {
             Toast.makeText(this, "Введите корректное число", Toast.LENGTH_SHORT).show()
        }
    }
}






