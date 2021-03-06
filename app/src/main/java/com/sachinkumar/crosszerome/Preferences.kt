package com.sachinkumar.crosszerome

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_preferences.*
import kotlinx.android.synthetic.main.fragment_preferences.view.*
import kotlinx.android.synthetic.main.fragment_welcome_screen.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Preferences.newInstance] factory method to
 * create an instance of this fragment.
 */
class Preferences : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_preferences, container, false)

        var mediaPlayer = MediaPlayer.create(context,R.raw.buttonclicksound)

        val playertype = arguments?.getString("buttonselected")



        if(playertype=="singlePlayer")
        {
            view.smallboard.isEnabled = false
            view.largeboard.isEnabled = false

            view.beginner.isSelected = true
            view.jod.isSelected = false
        }
        else
        {
            view.beginner.isEnabled = false
            view.jod.isEnabled = false

            view.smallboard.isSelected = true
            view.largeboard.isSelected = false
        }

        view.beginner.setOnClickListener {
            beginner.isSelected = true
            jod.isSelected = false
        }

        view.jod.setOnClickListener {
            jod.isSelected = true
            beginner.isSelected = false
        }

        view.smallboard.setOnClickListener {
            smallboard.isSelected = true
            largeboard.isSelected = false
        }

        view.largeboard.setOnClickListener {
            largeboard.isSelected = true
            smallboard.isSelected = false
        }





        view.preferencesSelected.setOnClickListener {
            mediaPlayer.start()
            if(view.beginner.isSelected)
            {
                Log.println(Log.DEBUG,"Choosen","1")
                val bundle = bundleOf("difficulty" to "beginner")
                Navigation.findNavController(view).navigate(R.id.action_preferences_to_multiplayer_AI,bundle)
            }
            else if(view.jod.isSelected)
            {  Log.println(Log.DEBUG,"Choosen","2")
                val bundle = bundleOf("difficulty" to "jod")
                Navigation.findNavController(view).navigate(R.id.action_preferences_to_multiplayer_AI,bundle)
            }
            else if(view.smallboard.isSelected)
            {   val bundle = bundleOf("boardtype" to "small")
                Navigation.findNavController(view).navigate(R.id.action_preferences_to_nameSelectionScreen,bundle)
            }
            else if(view.largeboard.isSelected)
            {   val bundle = bundleOf("boardtype" to "large")
                Navigation.findNavController(view).navigate(R.id.action_preferences_to_nameSelectionScreen,bundle)
            }
//
        }

        return view
    }


}