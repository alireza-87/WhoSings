package com.midnight.musictest.view

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.midnight.core.domain.ArtistModelCore
import com.midnight.core.domain.UserScoreModelCore
import com.midnight.core.helper.DataState
import com.midnight.musictest.R
import com.midnight.musictest.databinding.FragmentQuizeBinding
import com.midnight.musictest.viewmodel.QuizeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class QuizeFragment : Fragment() {
    private lateinit var binder:FragmentQuizeBinding
    private val viewModel:QuizeFragmentViewModel by viewModels()
    var page=1
    var trackId=-2L
    var artistList  : ArrayList<ArtistModelCore> = ArrayList()
    lateinit var answer : ArtistModelCore
    var lyricText : String = ""
    private var errorDialog: Snackbar? = null
    lateinit var answersClickListener:View.OnClickListener
    private val args: QuizeFragmentArgs by navArgs()
    private val maxQ=10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_quize,container,false)
        nextQuestion()
        initObserver()
        initActions()
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initActions(){
        answersClickListener = View.OnClickListener {
            if (it.tag == answer.artistId){ // right answer
                if (page==maxQ){ // win
                    viewModel.insertScore(UserScoreModelCore(userName = args.username, score = page, id = null, isWin = true))
                    win()
                }else{ // next question
                    nextQuestion()
                }
            }else{ // wrong answer
                viewModel.insertScore(UserScoreModelCore(userName = args.username, score = page-1, id = null, isWin = false))
                failed()
            }
        }
        binder.buttonAnswer1.setOnClickListener(answersClickListener)
        binder.buttonAnswer2.setOnClickListener(answersClickListener)
        binder.buttonAnswer3.setOnClickListener(answersClickListener)

        binder.buttonRestart.setOnClickListener {
            restart()
        }

        binder.buttonEnd.setOnClickListener {
            findNavController().navigateUp()
        }

        binder.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binder.buttonRetry.setOnClickListener {
            page -= 1
            hideError()
            nextQuestion()
        }
    }

    private fun initObserver(){
        viewModel.trackLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success -> {
                    trackId = it.value.trackId
                    artistList.add(ArtistModelCore(it.value.artistId,it.value.artistName!!))
                    answer = ArtistModelCore(it.value.artistId,it.value.artistName!!)
                    viewModel.getTwoArtist(true,it.value.artistId)
                }
                is DataState.Loading ->{
                    showLoading(true)
                }

                is DataState.NetworkError->{
                    showError()
                }
            }

        })

        viewModel.artistLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success -> {
                    artistList.addAll(it.value!!)
                    viewModel.getLyric(true,trackId)
                }
                is DataState.Loading ->{
                    showLoading(true)
                }
                is DataState.NetworkError->{
                    showError()
                }
            }
        })

        viewModel.lyricLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is DataState.Success ->{
                    lyricText = it.value?.lyric!!
                    showQuestion()
                    showLoading(false)
                }
                is DataState.Loading ->{
                    showLoading(true)
                }
                is DataState.NetworkError->{
                    showError()
                }
            }

        })
    }

    private fun nextQuestion(){
        clearQuestion()
        viewModel.getOneTrack(true,page,10,"it",1)
        page+=1
    }

    private fun clearQuestion(){
        binder.constraintLayoutQuestion.visibility = View.INVISIBLE
        binder.cardViewMain.visibility = View.INVISIBLE
        trackId=-2L
        artistList.clear()
        lyricText=""
    }

    private fun showQuestion(){
        binder.textviewLyric.text = lyricText
        if (artistList.size==3){
            val shuffledList=artistList.shuffled()
            binder.buttonAnswer1.text= shuffledList[0].artistName
            binder.buttonAnswer1.tag = shuffledList[0].artistId
            binder.buttonAnswer2.text= shuffledList[1].artistName
            binder.buttonAnswer2.tag = shuffledList[1].artistId
            binder.buttonAnswer3.text= shuffledList[2].artistName
            binder.buttonAnswer3.tag = shuffledList[2].artistId
        }

        binder.constraintLayoutQuestion.visibility = View.VISIBLE
        binder.cardViewMain.visibility = View.VISIBLE
        setPageNumber(page,10)

    }



    private fun failed(){
        clearQuestion()
        binder.cardViewMain.visibility = View.VISIBLE
        binder.constraintLayoutResult.visibility=View.VISIBLE
        binder.textViewStatus.text=getString(R.string.fail)
        binder.textViewScore.text = ""
        binder.textViewScore.append(string = (page-1).toString(),color = R.color.colorPrimary,bold = true)
        binder.textViewScore.append(" /${maxQ}", R.color.primary_text)
        page=0

    }

    private fun win(){
        clearQuestion()
        binder.cardViewMain.visibility = View.VISIBLE
        binder.constraintLayoutResult.visibility=View.VISIBLE
        binder.textViewStatus.text=getString(R.string.win)
        binder.textViewScore.text = ""
        binder.textViewScore.append(string = (page).toString(),color = R.color.colorPrimary,bold = true)
        binder.textViewScore.append(" /${maxQ}", R.color.primary_text)
        page=0
    }



    private fun restart(){
        clearQuestion()
        page=0
        binder.constraintLayoutResult.visibility=View.INVISIBLE
        nextQuestion()
    }

    private fun showError() {
        binder.progressBar.visibility = View.GONE
        binder.cardViewMain.visibility = View.VISIBLE
        binder.constraintLayoutQuestion.visibility = View.INVISIBLE
        binder.constraintLayoutError.visibility = View.VISIBLE
    }

    private fun hideError() {
        binder.constraintLayoutError.visibility = View.INVISIBLE
    }

    private fun showLoading(isShow:Boolean){
        when (isShow){
            true ->{
                binder.progressBar.visibility = View.VISIBLE
            }
            false ->{
                binder.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        errorDialog?.dismiss()
        binder.unbind()
    }

    private fun setPageNumber(number:Int,max:Int){
        binder.progressBarPageNumber.max = max
        binder.progressBarPageNumber.progress = number
    }

    fun TextView.append(string: String?, @ColorRes color: Int, bold:Boolean=false) {
        if (string == null || string.isEmpty()) {
            return
        }

        val spannable: Spannable = SpannableString(string)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, color)),
            0,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (bold)
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                spannable.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

        append(spannable)
    }



}