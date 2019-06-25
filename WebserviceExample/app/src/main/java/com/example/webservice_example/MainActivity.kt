package com.example.webservice_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, WSFragment())
            .commit()
    }

    fun GoToFragmentDetail(game_id: Int) {
        val fragment = DetailGameFragment()
        fragment.setGameId(game_id)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    fun GoToHangMan(name: String) {
        val fragment = Hangman_Main_Page()
        fragment.setPlayer(name)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    fun GoToPuzzle(name: String) {
        val fragment = Puzzle_Fragment()
        fragment.setPlayer(name)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    fun GoToHome(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, WSFragment())
            .commit()
    }

    fun GoToScore(score : Int, name : String) {
        val fragment = Score_Fragment()
        fragment.setName(name)
        fragment.setResult(score)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

}
