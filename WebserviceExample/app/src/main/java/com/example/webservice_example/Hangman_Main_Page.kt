package com.example.webservice_example


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_hangman__main__page.*
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 *
 */
class Hangman_Main_Page : Fragment() {

    private val word_list = listOf<String>("securite", "ecureuil", "vacciner", "insoumis", "intimite")
    private var vie = 0
    var choosen_word = word_list[Random.nextInt(0, 5)]
    private var player_name = "unknown player"

    fun setPlayer(name : String) {
        player_name = name
    }

    fun searchViewId(view : View, index : Int) : TextView {
        return when(index) {
            0 -> view.findViewById(hidden_word0.id)
            1 -> view.findViewById(hidden_word1.id)
            2 -> view.findViewById(hidden_word2.id)
            3 -> view.findViewById(hidden_word3.id)
            4 -> view.findViewById(hidden_word4.id)
            5 -> view.findViewById(hidden_word5.id)
            6 -> view.findViewById(hidden_word6.id)
            else -> view.findViewById(hidden_word7.id)
        }
    }

    fun checkEndGame(view : View, word : String, name : String) : Int {
        if (vie >= 11)
            (activity as MainActivity).GoToScore(1, name)
        var index = 0

        while (index < 8) {
            val t = searchViewId(view, index)
            if (t.text[0] != word[index])
                return 0
            index += 1
        }
        if (index == 8)
            (activity as MainActivity).GoToScore(0, name)
        return 0
    }

    fun introduceLetter(view : View, word : String) {
        var edittext : EditText = view.findViewById(R.id.input_letter)
        var letter : String = edittext.text.toString()
        if (letter.trim().length > 0) {
            var letter_in_word: Boolean = false
            if (letter.trim().length == 1) {
                var index: Int = 0
                while (index < 8) {
                    if (word[index] == letter[0]) {
                        letter_in_word = true
                        val t = searchViewId(view, index)
                        t.text = letter
                    }
                    index += 1
                }
            }
            if (letter_in_word == false) {
                vie += 1
                var img: ImageView = view.findViewById(hang_image.id) as ImageView
                when (vie) {
                    1 -> img.setImageResource(R.drawable.pendu_1)
                    2 -> img.setImageResource(R.drawable.pendu_2)
                    3 -> img.setImageResource(R.drawable.pendu_3)
                    4 -> img.setImageResource(R.drawable.pendu_4)
                    5 -> img.setImageResource(R.drawable.pendu_5)
                    6 -> img.setImageResource(R.drawable.pendu_6)
                    7 -> img.setImageResource(R.drawable.pendu_7)
                    8 -> img.setImageResource(R.drawable.pendu_8)
                    9 -> img.setImageResource(R.drawable.pendu_9)
                    10 -> img.setImageResource(R.drawable.pendu_10)
                    else -> img.setImageResource(R.drawable.pendu_11)
                }
            }
            checkEndGame(view, word, player_name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        choosen_word = word_list[Random.nextInt(0, 5)]
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hangman__main__page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            validate.setOnClickListener {
                introduceLetter(view, choosen_word)
            }
    }


}
