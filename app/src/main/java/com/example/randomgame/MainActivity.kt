package com.example.randomgame
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityMainBinding
    lateinit var randomGame : RandomGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        randomGame = RandomGame(viewBinding, applicationContext )




        viewBinding.bt1.setOnClickListener{

            randomGame.randomGame()




        }

    }
}