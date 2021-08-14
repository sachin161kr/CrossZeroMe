package com.sachinkumar.crosszerome

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_multiplayer__a_i.view.*
import kotlinx.android.synthetic.main.fragment_preferences.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [multiplayer_AI.newInstance] factory method to
 * create an instance of this fragment.
 */
class multiplayer_AI : Fragment() , View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var winSound : MediaPlayer
    lateinit var loseSound : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        winSound = MediaPlayer.create(context,R.raw.winsound)
        loseSound = MediaPlayer.create(context,R.raw.losesound)

    }

    lateinit var board: Array<Array<Button>>
    var gameDifficulty : String? = ""

    var charBoard = arrayOf(arrayOf("_","_","_"),arrayOf("_","_","_"),arrayOf("_","_","_"))

    var list = arrayListOf(0,1,2,3,4,5,6,7,8)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_multiplayer__a_i, container, false)
        var mediaPlayer = MediaPlayer.create(context,R.raw.buttonclicksound)
        gameDifficulty = arguments?.getString("difficulty")
        view.displayTv_M.text = "You vs Computer"

        board = arrayOf(
            arrayOf(view.Button1_M, view.Button2_M, view.Button3_M),
            arrayOf(view.Button4_M, view.Button5_M, view.Button6_M),
            arrayOf(view.Button7_M, view.Button8_M, view.Button9_M)
        )
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j].setOnClickListener(this)
            }
        }

        view.resetButton_M.setOnClickListener {
           mediaPlayer.start()
            for(i in 0..2)
            {
                for(j in 0..2)
                {
                    board[i][j].apply {
                        text = " "
                        isEnabled = true
                    }

                    charBoard[i][j] = "_"
                }
            }
            list = arrayListOf(0,1,2,3,4,5,6,7,8)
          view.displayTv_M.text = "You vs Computer"
        }

        return view

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.Button1_M -> {
                    v.Button1_M.apply {
                        text = "X"
                        isEnabled = false
                    }
                    charBoard[0][0] = "X"
                    list.remove(0)
                    checkWinner()
                }
                R.id.Button2_M -> {
                    v.Button2_M.apply {
                        text = "X"
                        isEnabled = false
                    }
                    charBoard[0][1] = "X"
                    list.remove(1)
                    checkWinner()
                }
                R.id.Button3_M -> {
                    v.Button3_M.apply {
                        text = "X"
                        isEnabled = false
                    }
                    charBoard[0][2] = "X"
                    list.remove(2)
                    checkWinner()
//
                }
                R.id.Button4_M -> {
                    v.Button4_M.apply {
                        text = "X"
                        isEnabled = false
                    }
                    charBoard[1][0] = "X"
                    list.remove(3)
                    checkWinner()
//
                }
                R.id.Button5_M -> {
                    v.Button5_M.apply {
                        text = "X"
                        isEnabled = false
                    }

                    charBoard[1][1] = "X"
                    list.remove(4)

                    checkWinner()
//
                }
                R.id.Button6_M -> {
                    v.Button6_M.apply {
                        text = "X"
                        isEnabled = false
                    }
                    charBoard[1][2] = "X"
                    list.remove(5)

                    checkWinner()
                }
                R.id.Button7_M -> {
                    v.Button7_M.apply {
                        text = "X"
                        isEnabled = false
                    }
                    charBoard[2][0] = "X"
                    list.remove(6)
                    checkWinner()
                }
                R.id.Button8_M -> {
                    v.Button8_M.apply {
                        text = "X"
                        isEnabled = false
                    }
                    charBoard[2][1] = "X"
                    list.remove(7)
                    checkWinner()
                }
                R.id.Button9_M -> {
                    v.Button9_M.apply {
                        text = "X"
                        isEnabled = false
                    }
                    charBoard[2][2] = "X"
                    list.remove(8)
                    checkWinner()
                }


            }

            if(isMovesLeft())
            {
                   val move : Move = if(gameDifficulty=="beginner")
                   {
                       getRandomMove()
                   }
                  else
                   {
                       findBestMove()
                   }
                   charBoard[move.row][move.col] = "O"
                   board[move.row][move.col].apply {
                       text = "O"
                       isEnabled = false
                   }
                checkWinner()

            }
            else {
                loseSound.start()
                requireView().displayTv_M.text = "Game Draw"
                disableAll()
            }

        }
    }

    private fun checkWinner() {
        for(i in 0..2)
        {
            if(board[i][0].text=="X" && board[i][1].text=="X" && board[i][2].text=="X")
            {
                updateDisplay("You Won")
                break
            }
            else if(board[i][0].text=="O" && board[i][1].text=="O" && board[i][2].text=="O")
            {
                updateDisplay("Computer Won")
                break
            }


        }

        for(j in 0..2)
        {
            if(board[0][j].text=="X" && board[1][j].text=="X" && board[2][j].text=="X")
            {
                updateDisplay("You Won")
                break
            }
            else if(board[0][j].text=="O" && board[1][j].text=="O" && board[2][j].text=="O")
            {
                updateDisplay("Computer Won")
                break
            }
        }

        if(board[0][0].text=="X" && board[1][1].text=="X" && board[2][2].text=="X")
        {
            updateDisplay("You Won")
        }
        else if(board[0][0].text=="O" && board[1][1].text=="O" && board[2][2].text=="O")
        {
            updateDisplay("Computer Won")
        }

        if(board[0][2].text=="X" && board[1][1].text=="X" && board[2][0].text=="X")
        {
            updateDisplay("You Won")
        }
        else if(board[0][2].text=="O" && board[1][1].text=="O" && board[2][0].text=="O")
        {
            updateDisplay("Computer Won")
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
        requireView().displayTv_M.text = s
        if(s.contains("Won"))
        {
            if(s=="You Won")
            {
                winSound.start()

            }
            else
            {
                loseSound.start()

            }

            disableAll()
        }



    }

    private fun minimax (depth:Int, isMax : Boolean) : Int
    {
        val score = evaluate()

        if(score==10)
        {
            return score
        }

        if(score==-10)
        {
            return score
        }

        if(!isMovesLeft())
        {
            return 0
        }

        if(isMax)
        {
            var best = -1000

            for(i in 0..2)
            {
                for(j in 0..2)
                {
                    if(charBoard[i][j]=="_")
                    {
                        charBoard[i][j] = "O"
//                        board[i][j].apply {
//                            text = "O"
//                            isEnabled = false
//                        }
                        best = Math.max(best,minimax(depth+1,!isMax))
//                        board[i][j].apply {
//                            text = " "
//                            isEnabled = true
//                        }
                        charBoard[i][j] = "_"
                    }
                }
            }

            return best
        }

        else
        {
            var best = 1000
            for(i in 0..2)
            {
                for(j in 0..2)
                {
                    if(charBoard[i][j]=="_")
                    {
                        charBoard[i][j] = "X"
//                        board[i][j].apply {
//                            text = "X"
//                            isEnabled = false
//                        }

                        best = Math.min(best,minimax(depth+1,!isMax))

//                        board[i][j].apply {
//                            text = " "
//                            isEnabled = true
//                        }
                        charBoard[i][j] = "_"
                    }
                }
            }

            return best
        }

    }

    private fun getRandomMove() : Move
    {

        val randomMove = Move()
        randomMove.row = -1
        randomMove.col = -1

        val e = list.random()
        list.remove(e)
        when(e)
        {
            0 ->
            {
                randomMove.row = 0
                randomMove.col = 0

            }

            1 ->
            {
                randomMove.row = 0
                randomMove.col = 1
            }

            2 ->
            {
                randomMove.row = 0
                randomMove.col = 2
            }

            3 ->
            {
                randomMove.row = 1
                randomMove.col = 0
            }

            4 ->
            {
                randomMove.row = 1
                randomMove.col = 1
            }

            5 ->
            {
                randomMove.row = 1
                randomMove.col = 2
            }

            6 ->
            {
                randomMove.row = 2
                randomMove.col = 0
            }

            7 ->
            {
                randomMove.row = 2
                randomMove.col = 1
            }

            8 ->
            {
                randomMove.row = 2
                randomMove.col = 2
            }

        }

        return randomMove
    }

    private fun findBestMove() : Move
    {
        var bestVal = -1000
        val bestMove = Move()

        bestMove.row = -1
        bestMove.col = -1

        for(i in 0..2)
        {
            for(j in 0..2)
            {
                if(charBoard[i][j]== "_")
                {
                    charBoard[i][j] = "O"
//                    board[i][j].apply {
//                        board[i][j].text = "O"
//
//                        isEnabled = false
//                    }

                    val moveVal = minimax(0,false)

                    charBoard[i][j] = "_"

//                    board[i][j].apply {
//                        board[i][j].text = " "
//
//                        isEnabled = true
//                    }

                    if(moveVal>bestVal)
                    {
                        bestMove.row = i
                        bestMove.col = j
                        bestVal = moveVal
                    }



                }
            }
        }

        return bestMove
    }

    private fun isMovesLeft() : Boolean
    {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                if(charBoard[i][j]=="_")
                {
                    return true
                }
            }
        }

        return false
    }

    private fun evaluate() : Int
    {
        for(i in 0..2)
        {
            if(charBoard[i][0]==charBoard[i][1] && charBoard[i][0]==charBoard[i][2])
            {
                if(charBoard[i][0]=="O")
                {
                    return +10
                }
                else if(charBoard[i][0]=="X")
                {
                    return -10
                }
            }

        }

        for(j in 0..2)
        {
            if(charBoard[0][j]==charBoard[1][j] && charBoard[0][j]==charBoard[2][j])
            {
                if(charBoard[0][j]=="O")
                {
                    return +10
                }
                else if(charBoard[0][j]=="X")
                {
                    return -10
                }
            }
        }

        if(charBoard[0][0]==charBoard[1][1] && charBoard[0][0]==charBoard[2][2])
        {
            if(charBoard[0][0]=="O")
            {
                return +10
            }
            else if(charBoard[0][0]=="X")
            {
                return -10
            }
        }

        if(charBoard[0][2]==charBoard[1][1] && charBoard[0][2]==charBoard[2][0])
        {
            if(charBoard[0][2]=="O")
            {
                return +10
            }
            else if(charBoard[0][2]=="X")
            {
                return -10
            }
        }

        return 0
    }



}