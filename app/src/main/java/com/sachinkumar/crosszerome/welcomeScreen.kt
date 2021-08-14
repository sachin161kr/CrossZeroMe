package com.sachinkumar.crosszerome

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_welcome_screen.*
import kotlinx.android.synthetic.main.fragment_welcome_screen.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [welcomeScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class welcomeScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //var startSound = MediaPlayer.create(context,R.raw.drums)
    lateinit var mediaPlayer : MediaPlayer
    lateinit var startSound : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        mediaPlayer = MediaPlayer.create(context,R.raw.buttonclicksound)
        startSound = MediaPlayer.create(context,R.raw.bgsound)

        startSound.isLooping = true


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment




        val view = inflater.inflate(R.layout.fragment_welcome_screen, container, false)
        view.multiPlayer.setOnClickListener {
            //startSound.pause()
            mediaPlayer.start()
            val bundle = bundleOf("buttonselected" to "multiPlayer")
            Navigation.findNavController(view).navigate(R.id.action_welcomeScreen_to_preferences,bundle)
        }

        view.exit_btn.setOnClickListener {
            activity?.finish()
        }

        view.singlePlayer.setOnClickListener {
            //startSound.pause()
            mediaPlayer.start()
            val bundle = bundleOf("buttonselected" to "singlePlayer")
            Navigation.findNavController(view).navigate(R.id.action_welcomeScreen_to_preferences,bundle)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        startSound.start()
    }

//    override fun onStop() {
//        super.onStop()
//        startSound.start()
//    }

    override fun onDestroy() {
        super.onDestroy()
        startSound.stop()
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        gmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:mail.sachin161@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT,"Regarding Cross Zero Me")
            startActivity(intent)
        }

        linkenin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.linkedin.com/in/sachin-kumar-29287a68/")

            startActivity(intent)
        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment welcomeScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            welcomeScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}