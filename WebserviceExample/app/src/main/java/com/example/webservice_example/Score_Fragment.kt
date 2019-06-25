package com.example.webservice_example


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_score_.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Score_Fragment : Fragment() {

    private var result : Int = 0

    private var name : String = "unknown player"

    fun setResult(res : Int) {
        result = res
    }

    fun setName(player_name : String) {
        name = player_name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        player_name.text = name
        if (result == 0)
            WinLooseText.text = "You Win !"
        else
            WinLooseText.text = "You Loose !"
        Menu.setOnClickListener {
            (activity as MainActivity).GoToHome()
        }
    }


}
