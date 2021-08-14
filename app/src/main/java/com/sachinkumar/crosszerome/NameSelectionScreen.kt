package com.sachinkumar.crosszerome

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_name_selection_screen.*
import kotlinx.android.synthetic.main.fragment_name_selection_screen.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [NameSelectionScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class NameSelectionScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_name_selection_screen, container, false)
        var mediaPlayer = MediaPlayer.create(context,R.raw.buttonclicksound)
        val boardtype = arguments?.getString("boardtype")

        view.start_button.setOnClickListener {
            mediaPlayer.start()
            if(view.name_playerX.text.length !=0 && view.name_playerO.text.length!=0)
            {
                val bundle = bundleOf("playerX" to view.name_playerX.text.toString() , "playerO" to view.name_playerO.text.toString())

                if(boardtype=="small")
                {
                    Navigation.findNavController(view).navigate(R.id.action_nameSelectionScreen_to_gameFragment,bundle)
                }
                else
                {
                    Navigation.findNavController(view).navigate(R.id.action_nameSelectionScreen_to_singlePlayer_5x5,bundle)
                }
            }
            else {
                Toast.makeText(activity,"Player names cannot be empty!",Toast.LENGTH_SHORT).show()
            }


        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NameSelectionScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NameSelectionScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}