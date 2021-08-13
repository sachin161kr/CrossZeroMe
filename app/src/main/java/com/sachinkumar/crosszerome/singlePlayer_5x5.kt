package com.sachinkumar.crosszerome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_multiplayer__a_i.view.*
import kotlinx.android.synthetic.main.fragment_single_player_5x5.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [singlePlayer_5x5.newInstance] factory method to
 * create an instance of this fragment.
 */
class singlePlayer_5x5 : Fragment() , View.OnClickListener{
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

    var scoreX = 0
    var scoreO = 0

    var playerName1 = "Player X"
    var playerName2 = "Player O"


    lateinit var board: Array<Array<Button>>

    //var charBoard = arrayOf(arrayOf("_","_","_","_","_"),arrayOf("_","_","_","_","_"),arrayOf("_","_","_","_","_"),arrayOf("_","_","_","_","_"),arrayOf("_","_","_","_","_"))

    var boardStatus = arrayOf(arrayOf(-1, -2, -3,-4,-5), arrayOf(-6, -7, -8,-9,-10), arrayOf(-11, -12, -13,-14,-15),arrayOf(-16, -17, -18,-19,-20),arrayOf(-21, -22, -23,-24,-25))
    //var list = arrayListOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_single_player_5x5, container, false)
        //gameDifficulty = arguments?.getString("difficulty")
        val firstTxt = arguments?.getString("playerX")
        view.displayTv_5x5.text = "${firstTxt}'s Turn"

        board = arrayOf(
            arrayOf(view.Button1_5x5, view.Button2_5x5, view.Button3_5x5,view.Button4_5x5,view.Button5_5x5),
            arrayOf(view.Button6_5x5, view.Button7_5x5, view.Button8_5x5,view.Button9_5x5,view.Button10_5x5),
            arrayOf(view.Button11_5x5, view.Button12_5x5, view.Button13_5x5,view.Button14_5x5,view.Button15_5x5),
            arrayOf(view.Button16_5x5,view.Button17_5x5,view.Button18_5x5,view.Button19_5x5,view.Button20_5x5),
            arrayOf(view.Button21_5x5,view.Button22_5x5,view.Button23_5x5,view.Button24_5x5,view.Button25_5x5)
        )

        for (i in 0..4) {
            for (j in 0..4) {
                board[i][j].setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        view.resetButton_5x5.setOnClickListener {
            initializeBoardStatus()
            PLAYER = true
            TURN_COUNT = 0
            //list = arrayListOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24)
            view.displayTv_5x5.text = "${playerName1}'s Turn"
        }

        return view
    }

    private fun initializeBoardStatus() {
       // boardStatus = arrayOf(arrayOf(-1, -2, -3), arrayOf(-4, -5, -6), arrayOf(-7, -8, -9))
        boardStatus = arrayOf(arrayOf(-1, -2, -3,-4,-5), arrayOf(-6, -7, -8,-9,-10), arrayOf(-11, -12, -13,-14,-15),arrayOf(-16, -17, -18,-19,-20),arrayOf(-21, -22, -23,-24,-25))
        for (i in 0..4) {
            for (j in 0..4) {
                board[i][j].text = ""
                board[i][j].isEnabled = true
            }
        }


    }



    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.Button1_5x5 -> {
                    updateValue(row = 0, col = 0, player = PLAYER)
                }
                R.id.Button2_5x5 -> {
                    updateValue(row = 0, col = 1, player = PLAYER)
                }
                R.id.Button3_5x5 -> {
                    updateValue(row = 0, col = 2, player = PLAYER)
//
                }
                R.id.Button4_5x5 -> {
                    updateValue(row = 0, col = 3, player = PLAYER)
//
                }
                R.id.Button5_5x5 -> {
                    updateValue(row = 0, col = 4, player = PLAYER)
//
                }
                R.id.Button6_5x5 -> {
                    updateValue(row = 1, col = 0, player = PLAYER)
                }
                R.id.Button7_5x5 -> {
                    updateValue(row = 1, col = 1, player = PLAYER)
                }
                R.id.Button8_5x5 -> {
                    updateValue(row = 1, col = 2, player = PLAYER)
                }
                R.id.Button9_5x5 -> {
                    updateValue(row = 1, col = 3, player = PLAYER)
                }
                R.id.Button10_5x5 -> {
                    updateValue(row = 1, col = 4, player = PLAYER)
                }
                R.id.Button11_5x5 -> {
                    updateValue(row = 2, col = 0, player = PLAYER)
                }
                R.id.Button12_5x5 -> {
                    updateValue(row = 2, col = 1, player = PLAYER)
                }
                R.id.Button13_5x5 -> {
                    updateValue(row = 2, col = 2, player = PLAYER)
                }
                R.id.Button14_5x5 -> {
                    updateValue(row = 2, col = 3, player = PLAYER)
                }
                R.id.Button15_5x5 -> {
                    updateValue(row = 2, col = 4, player = PLAYER)
                }
                R.id.Button16_5x5 -> {
                    updateValue(row = 3, col = 0, player = PLAYER)
                }
                R.id.Button17_5x5 -> {
                    updateValue(row = 3, col = 1, player = PLAYER)
                }
                R.id.Button18_5x5 -> {
                    updateValue(row = 3, col = 2, player = PLAYER)
                }
                R.id.Button19_5x5 -> {
                    updateValue(row = 3, col = 3, player = PLAYER)
                }
                R.id.Button20_5x5 -> {
                    updateValue(row = 3, col = 4, player = PLAYER)
                }
                R.id.Button21_5x5 -> {
                    updateValue(row = 4, col = 0, player = PLAYER)
                }
                R.id.Button22_5x5 -> {
                    updateValue(row = 4, col = 1, player = PLAYER)
                }
                R.id.Button23_5x5 -> {
                    updateValue(row = 4, col = 2, player = PLAYER)
                }
                R.id.Button24_5x5 -> {
                    updateValue(row = 4, col = 3, player = PLAYER)
                }
                R.id.Button25_5x5 -> {
                    updateValue(row = 4, col = 4, player = PLAYER)
                }


            }

            TURN_COUNT++
            PLAYER = (!PLAYER)

            if (PLAYER) {
                updateDisplay("${playerName1}'s Turn")
            } else {
                updateDisplay("${playerName2}'s Turn")
            }

            if (TURN_COUNT == 25) {
                updateDisplay("Game Draw")
            }

            checkWinner()



        }
    }

    private fun checkWinner() {
        for(i in 0..4)
        {
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][1]==boardStatus[i][2] && boardStatus[i][2]==boardStatus[i][3] && boardStatus[i][3]==boardStatus[i][4])
            {
                if(boardStatus[i][0]==1)
                {   scoreX++
                    requireView().point_X_5x5.text = "X : $scoreX"
                    updateDisplay("$playerName1 Won")
                }
                else
                {   scoreO++
                    requireView().point_O_5x5.text = "O : $scoreO"
                    updateDisplay("$playerName2 Won")
                }

                break
            }



        }

        for(j in 0..4) {
            if (boardStatus[0][j] == boardStatus[1][j] && boardStatus[1][j] == boardStatus[2][j] && boardStatus[2][j] == boardStatus[3][j] && boardStatus[3][j] == boardStatus[4][j]) {

                if (boardStatus[0][j] == 1) {
                    scoreX++
                    requireView().point_X_5x5.text = "X : $scoreX"
                    updateDisplay("$playerName1 Won")
                } else {
                    scoreO++
                    requireView().point_O_5x5.text = "O : $scoreO"
                    updateDisplay("$playerName2 Won")
                }

                break
            }
        }

        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][2] && boardStatus[2][2]==boardStatus[3][3] && boardStatus[3][3]==boardStatus[4][4])
        {
            if(boardStatus[0][0]==1)
            {
                scoreX++
                requireView().point_X_5x5.text = "X : $scoreX"
                updateDisplay("$playerName1 Won")
            }
            else
            {
                scoreO++
                requireView().point_O_5x5.text = "O : $scoreO"
                updateDisplay("$playerName2 Won")
            }
        }


       if(boardStatus[0][4]==boardStatus[1][3] && boardStatus[1][3]==boardStatus[2][2] && boardStatus[2][2]==boardStatus[3][1] && boardStatus[3][1]==boardStatus[4][0])
       {
           if(boardStatus[0][4]==1)
           {
               scoreX++
               requireView().point_X_5x5.text = "X : $scoreX"
               updateDisplay("$playerName1 Won")
           }
           else
           {
               scoreO++
               requireView().point_O_5x5.text = "O : $scoreO"
               updateDisplay("$playerName2 Won")
           }
       }

    }

    private fun disableAll() {
        for (i in 0..4) {
            for (j in 0..4) {
                board[i][j].isEnabled = false
            }
        }
    }

    private fun updateDisplay(s: String) {
        requireView().displayTv_5x5.text = s
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