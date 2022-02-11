package com.midnight.musictest.view

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.*
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.midnight.core.domain.QueryUsersScoresCore
import com.midnight.core.helper.DataState
import com.midnight.musictest.R
import com.midnight.musictest.databinding.FragmentMainBinding
import com.midnight.musictest.view.adapter.AdapterScoreChart
import com.midnight.musictest.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_detail.view.*


@AndroidEntryPoint
class MainFragment:Fragment() {

    private lateinit var binder:FragmentMainBinding
    private val viewMode:MainFragmentViewModel by viewModels()
    private lateinit var chartAdapter: AdapterScoreChart
    private var listLayoutManager:LinearLayoutManager ? = null
    private lateinit var userName:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        initActions()
        initLiveData()
        initListView()
        viewMode.getCurrentUser()
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun initLiveData(){
        viewMode.userLiveData.observe(viewLifecycleOwner, Observer {
            when (it){
                is DataState.Success->{
                    userName=it.value!!.userName
                    viewMode.getScoreList()
                }
            }

        })
        viewMode.logoutLiveData.observe(viewLifecycleOwner, Observer {
            when (it){
                is DataState.Loading ->{}
                is DataState.LocalError ->{}
                is DataState.Success ->{ findNavController().navigate(MainFragmentDirections.actionMainFragmentToLogin())}
            }
        })

        viewMode.scoresLiveData.observe(viewLifecycleOwner, Observer {
            when (it){
                is DataState.Loading ->{}
                is DataState.LocalError ->{}
                is DataState.Success ->{ initView(it.value)}
            }
        })
    }

    private fun initActions(){
        binder.buttonExit.setOnClickListener {
            viewMode.logout(userName)

        }
        binder.buttonStartQuize.setOnClickListener {
            openQuiz()
        }
    }

    private fun initView(value: List<QueryUsersScoresCore>?) {
        val myUser=getMyScores(value)
        value?.let { chartAdapter.update(it) }
        binder.userDetailComp.text_view_user_name.text = myUser.userModel.userName
        binder.userDetailComp.text_view_user_score.text = ""
        binder.userDetailComp.text_view_user_score.append(string = (myUser.totalWin).toString(),color = R.color.colorPrimary,bold = true)
        binder.userDetailComp.text_view_user_score.append(" /${myUser.scoreList?.size}", R.color.primary_text)

    }

    private fun initListView(){
        chartAdapter = AdapterScoreChart()
        binder.recyclerviewTopPlayer.apply {
            listLayoutManager = LinearLayoutManager(requireContext())
            layoutManager = listLayoutManager
            setHasFixedSize(true)
            adapter = chartAdapter
        }

    }

    private fun getMyScores(data:List<QueryUsersScoresCore>?):QueryUsersScoresCore{
        val result = data?.filter { it.userModel.userName.equals(userName,true) }
        return result!![0]
    }

    private fun openQuiz(){
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToQuizeFragment(userName))
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