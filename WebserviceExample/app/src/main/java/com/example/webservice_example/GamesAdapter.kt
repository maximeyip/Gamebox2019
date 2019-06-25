package com.example.webservice_example

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_ws.*
import kotlinx.android.synthetic.main.mylist.*

class GamesAdapter(private val context: Context, private val data: MutableList<GameList>) : BaseAdapter() {
    override fun getCount(): Int {
        // returns the number of items in this adapter
        // usually the size of the underlying List/Array...
        return data.size }
    override fun getItem(position: Int): GameList {
        // returns the data item at the specified position in the list
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        // return the ID of the specified row (customized or row position)
        return position.toLong()
    }

    // called by the system whenever a row must be drawn on the screen
    // the getView() method receives a position and must return the
    // corresponding row view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // first let us retrieve the item at the specified position
        val currentItem: GameList = getItem(position)
        // now we build a view
        // first step, acquire a LayoutInflater
        val layoutInflater = LayoutInflater.from(context)
        // now use this LayoutInflater to inflate our row layout resource into a View
        val rowView = layoutInflater.inflate(R.layout.mylist, parent, false);

        // bind variables to the distinct views inside our row view
        val nameTextView = rowView.findViewById<TextView>(R.id.textViewList)

        // finally, time to put the data in here
        nameTextView.text = currentItem.name

        val pictureView = rowView.findViewById<ImageView>(R.id.imageView2)
        Glide.with(context).load(currentItem.picture).into(pictureView)


        if (currentItem.name.equals("SlidingPuzzle") || currentItem.name.equals("Hangman")) {
            val play_button = rowView.findViewById<ImageView>(R.id.imageView3)
            play_button.setImageResource(R.drawable.play_button)
            play_button.setVisibility(View.VISIBLE)
            // play_button.setOnClickListener()
        }
        // job’s done, the view is built and contains the data for the
        // requested position, we can return it to the system
        return rowView;
    }
}
