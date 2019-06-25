package com.example.webservice_example


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.empty_list.*
import kotlinx.android.synthetic.main.fragment_ws.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class WSFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ws, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val baseURL = "http://androidlessonsapi.herokuapp.com/api/"
        // Use GSON library to create our JSON parserval
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        // Create a Retrofit client object targeting the provided URL
        // and add a JSON converter (because we are expecting json responses)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()
        // Use the client to create a service:
        // an object implementing the interface to the WebServiceval
        val service: WSinterface = retrofit.create(WSinterface::class.java)
        val wsCallBack: Callback<List<GameList>> = object : Callback<List<GameList>> {
            override fun onFailure(call: Call<List<GameList>>, t: Throwable) {
                // Code here what happens if calling the WebService fails
                Log.w("TAG", "WebService call failed")
            }

            override fun onResponse(call: Call<List<GameList>>, response: Response<List<GameList>>) {
                if (response.code() == 200) {
                    // We got our data !
                    val responseData = response.body()
                    if (responseData != null) {
                        val listItems: MutableList<GameList> = arrayListOf()

                        for (i in responseData) {
                            listItems.add(i)
                        }
                        listViewId.adapter = activity?.let { GamesAdapter(it, listItems) }
                        listViewId.setOnItemClickListener { adapterView, clickedView, position, id ->
                            (activity as MainActivity).GoToFragmentDetail(responseData[position].id)
                        }
                        listViewId.emptyView = list_empty
                    }
                }
            }

        }
        service.getAllToDos().enqueue(wsCallBack)
    }
}
