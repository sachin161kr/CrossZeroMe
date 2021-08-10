package com.sachinkumar.crosszerome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_name_selection_screen.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() , View.OnClickListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        playerName1 = arguments?.getString("playerX").toString()
        playerName2 = arguments?.getString("playerO").toString()


    }

    var PLAYER = true
    var TURN_COUNT = 0

    var playerName1 = "Player X"
    var playerName2 = "Player O"

    var boardStatus = arrayOf(arrayOf(-1, -2, -3), arrayOf(-4, -5, -6), arrayOf(-7, -8, -9))
    lateinit var board: Array<Array<Button>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val firstTxt = arguments?.getString("playerX")


            view.displayTv.text = "${firstTxt}'s Turn"

        board = arrayOf(
            arrayOf(view.Button1, view.Button2, view.Button3),
            arrayOf(view.Button4, view.Button5, view.Button6),
            arrayOf(view.Button7, view.Button8, view.Button9)
        )
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j].setOnClickListener(this)
            }
        }

        initializeBoardStatus()



        view.resetButton.setOnClickListener {
            initializeBoardStatus()
            PLAYER = true
            TURN_COUNT = 0
            view.displayTv.text = "${playerName1}'s Turn"
        }

        return view

    }



    private fun initializeBoardStatus() {
        boardStatus = arrayOf(arrayOf(-1, -2, -3), arrayOf(-4, -5, -6), arrayOf(-7, -8, -9))
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j].text = ""
                board[i][j].isEnabled = true
            }
        }


    }
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.Button1 -> {
                    updateValue(row = 0, col = 0, player = PLAYER)
                }
                R.id.Button2 -> {
                    updateValue(row = 0, col = 1, player = PLAYER)
                }
                R.id.Button3 -> {
                    updateValue(row = 0, col = 2, player = PLAYER)
                }
                R.id.Button4 -> {
                    updateValue(row = 1, col = 0, player = PLAYER)
                }
                R.id.Button5 -> {
                    updateValue(row = 1, col = 1, player = PLAYER)
                }
                R.id.Button6 -> {
                    updateValue(row = 1, col = 2, player = PLAYER)
                }
                R.id.Button7 -> {
                    updateValue(row = 2, col = 0, player = PLAYER)
                }
                R.id.Button8 -> {
                    updateValue(row = 2, col = 1, player = PLAYER)
                }
                R.id.Button9 -> {
                    updateValue(row = 2, col = 2, player = PLAYER)
                }


            }

            TURN_COUNT++
            PLAYER = (!PLAYER)

            if (PLAYER) {
                updateDisplay("${playerName1}'s Turn")
            } else {
                updateDisplay("${playerName2}'s Turn")
            }

            if (TURN_COUNT == 9) {
                updateDisplay("Game Draw")
            }

            checkWinner()

        }
    }

    private fun checkWinner() {
        for(i in 0..2)
        {
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2])
            {
                //Log.d("my msg", "checkWinner: ${boardStatus[0][0]} ${boardStatus[0][1]} ${boardStatus[0][2]}")
                if(boardStatus[i][0]==1)
                {
                    updateDisplay("$playerName1 Won")
                }
                else
                {
                    updateDisplay("$playerName2 Won")
                }

                break
            }
        }

        for(j in 0..2)
        {
            if(boardStatus[0][j]==boardStatus[1][j] && boardStatus[0][j]==boardStatus[2][j])
            {
                //Log.d("my msg", "checkWinner: ${boardStatus[0][0]} ${boardStatus[0][1]} ${boardStatus[0][2]}")
                if(boardStatus[0][j]==1)
                {
                    updateDisplay("$playerName1 Won")
                }
                else
                {
                    updateDisplay("$playerName2 Won")
                }

                break
            }
        }

        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2])
        {
            if(boardStatus[0][0]==1)
            {
                updateDisplay("$playerName1 Won")
            }
            else
            {
                updateDisplay("$playerName2 Won")
            }

        }

        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0])
        {
            if(boardStatus[0][2]==1)
            {
                updateDisplay("$playerName1 Won")
            }
            else
            {
                updateDisplay("$playerName2 Won")
            }

        }

    }

    private fun disableAll() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j].isEnabled = false
            }
        }
    }

    private fun updateDisplay(s: String) {
        requireView().displayTv.text = s
        if(s.contains("Won"))
        {
            disableAll()
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)

        }
        boardStatus[row][col] = value
    }
}