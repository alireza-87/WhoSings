package com.midnight.musictest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.midnight.core.domain.QueryUsersScoresCore
import com.midnight.musictest.R
import com.midnight.musictest.databinding.ItemScoreChartBinding
import com.midnight.musictest.helper.DiffScores

class AdapterScoreChart constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: ArrayList<QueryUsersScoresCore> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binder: ItemScoreChartBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_score_chart,
            parent,
            false
        )
        return ScoreViewHolder(binder)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ScoreViewHolder).bind(data[position])


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else
            (holder as ScoreViewHolder).bind(data[position])
    }


    override fun getItemCount(): Int {
        return data.size
    }

    class ScoreViewHolder(@NonNull private val binder: ItemScoreChartBinding) : RecyclerView.ViewHolder(binder.root) {

        fun bind(data: QueryUsersScoresCore) {
            binder.data = data
            binder.executePendingBindings()
        }
    }

    public fun update(newData:List<QueryUsersScoresCore>){
//        var dataOld:ArrayList<QueryUsersScoresCore> = ArrayList()
//        dataOld.addAll(this.data)
//
//        this.data.clear()
//        this.data.addAll(newData)
        val diffResult = DiffUtil.calculateDiff(DiffScores(newData, this.data))
        diffResult.dispatchUpdatesTo(this)
        this.data.clear()
        this.data.addAll(newData)

    }


}