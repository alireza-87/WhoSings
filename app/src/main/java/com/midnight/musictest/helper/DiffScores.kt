package com.midnight.musictest.helper

import androidx.recyclerview.widget.DiffUtil
import com.midnight.core.domain.QueryUsersScoresCore

class DiffScores constructor(private val oldItems: List<QueryUsersScoresCore>, private  val newItems: List<QueryUsersScoresCore>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].userModel.userName.equals(newItems[newItemPosition].userModel.userName,true)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].compareTo(newItems[newItemPosition])==1
    }
}