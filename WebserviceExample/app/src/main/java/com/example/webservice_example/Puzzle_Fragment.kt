package com.example.webservice_example


import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import kotlinx.android.synthetic.main.fragment_puzzle_.*
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Puzzle_Fragment : Fragment() {

    private var image_list = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 9) // image actuelle
    private val imageview_list = arrayOfNulls<ImageButton>(9)
    private var fixed_image = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9) // res voulu
    private var player_name = "unknown player"

    val timer = object: CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            countdown.text = (millisUntilFinished / 1000).toString()
        }
        override fun onFinish() {
            (activity as MainActivity).GoToScore(1, player_name)
        }
    }

    fun setPlayer(name : String) {
        player_name = name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_puzzle_, container, false)
    }

    fun fillFlower() {
        fixed_image[0] = R.drawable.flower_0
        fixed_image[1] = R.drawable.flower_1
        fixed_image[2] = R.drawable.flower_2
        fixed_image[3] = R.drawable.flower_3
        fixed_image[4] = R.drawable.flower_4
        fixed_image[5] = R.drawable.flower_5
        fixed_image[6] = R.drawable.flower_6
        fixed_image[7] = R.drawable.flower_7
        fixed_image[8] = R.drawable.flower_8
    }

    fun fillPoke() {
        fixed_image[0] = R.drawable.poke_0
        fixed_image[1] = R.drawable.poke_1
        fixed_image[2] = R.drawable.poke_2
        fixed_image[3] = R.drawable.poke_3
        fixed_image[4] = R.drawable.poke_4
        fixed_image[5] = R.drawable.poke_5
        fixed_image[6] = R.drawable.poke_6
        fixed_image[7] = R.drawable.poke_7
        fixed_image[8] = R.drawable.poke_8
    }

    fun fillEarth() {
        fixed_image[0] = R.drawable.earth_0
        fixed_image[1] = R.drawable.earth_1
        fixed_image[2] = R.drawable.earth_2
        fixed_image[3] = R.drawable.earth_3
        fixed_image[4] = R.drawable.earth_4
        fixed_image[5] = R.drawable.earth_5
        fixed_image[6] = R.drawable.earth_6
        fixed_image[7] = R.drawable.earth_7
        fixed_image[8] = R.drawable.earth_8
    }

    fun init(view : View){
        val img_choice = Random.nextInt(0,3)
        when (img_choice) {
            0 -> fillFlower()
            1 -> fillPoke()
            else -> fillEarth()
        }
        fixed_image[9] = R.drawable.blank
        for (counter in 0..15) {
        for (i in 0..8) {
            val random = Random.nextInt(0, 9)
            move(view, random, false)
        }
        }
        for (i in 0..8) {
            val value = image_list[i]
            imageview_list[i]?.setImageResource(fixed_image[value])
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        imageview_list[0] = view.findViewById(image0.id)
        imageview_list[1] = view.findViewById(image1.id)
        imageview_list[2] = view.findViewById(image2.id)
        imageview_list[3] = view.findViewById(image3.id)
        imageview_list[4] = view.findViewById(image4.id)
        imageview_list[5] = view.findViewById(image5.id)
        imageview_list[6] = view.findViewById(image6.id)
        imageview_list[7] = view.findViewById(image7.id)
        imageview_list[8] = view.findViewById(image8.id)

        println("to be ready")
        init(view)
        println("ready")

        timer.start()
        image0.setOnClickListener {
            move(view, 0, true)
        }

        image1.setOnClickListener {
            move(view, 1, true)
        }

        image2.setOnClickListener {
            move(view, 2, true)
        }

        image3.setOnClickListener {
            move(view, 3, true)
        }

        image4.setOnClickListener {
            move(view, 4, true)
        }

        image5.setOnClickListener {
            move(view, 5, true)
        }

        image6.setOnClickListener {
            move(view, 6, true)
        }

        image7.setOnClickListener {
            move(view, 7, true)
        }
        image8.setOnClickListener {
            move(view, 8, true)
        }
    }

    fun move(view : View, index : Int, update : Boolean)
        {
        val tmp : Int = image_list[index]
        var target : Int = index
        while (true) {
            if (index % 3 != 0) {
                if (image_list[index - 1] == 9) {
                    image_list[index] = image_list[index - 1]
                    image_list[index - 1] = tmp
                    target = index - 1
                    break
                }
            }
            if (index >= 3) {
                if (image_list[index - 3] == 9) {
                    image_list[index] = image_list[index - 3]
                    image_list[index - 3] = tmp
                    target = index - 3
                    break
                }
            }
            if (index <= 5) {
                if (image_list[index + 3] == 9) {
                    image_list[index] = image_list[index + 3]
                    image_list[index + 3] = tmp
                    target = index + 3
                    break
                }
            }
            if (index % 3 != 2) {
                if (image_list[index + 1] == 9) {
                    image_list[index] = image_list[index + 1]
                    image_list[index + 1] = tmp
                    target = index + 1
                    break
                }
            }
            break
        }
        if (target != index && update) {
            update_image(view, index, target)
        }
        return
    }

    fun update_image(view : View, index : Int, target : Int) {

        val value = image_list[index]
        imageview_list[index]?.setImageResource(fixed_image[value])
        val value2 = image_list[target]
        imageview_list[target]?.setImageResource(fixed_image[value2])

        var ind = 0
        for (i in 0..8){
            if (image_list[i] == i || ((i == 8) && (image_list[i] == 9)))
                ind += 1
            else
                break
        }
        if (ind == 9) {
            timer.cancel()
            (activity as MainActivity).GoToScore(0, player_name)
        }
    }

}
