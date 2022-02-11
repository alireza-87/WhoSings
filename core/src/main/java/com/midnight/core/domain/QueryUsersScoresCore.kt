package com.midnight.core.domain

data class QueryUsersScoresCore(
    val userModel: UserModelCore,
    val scoreList:List<UserScoreModelCore>?,
    val totalWin:Int
):Comparable<QueryUsersScoresCore>{
    override fun compareTo(other: QueryUsersScoresCore): Int {
        return if ((other.scoreList?.size == this.scoreList?.size) && (other.totalWin == this.totalWin))
            1
        else
            0
    }
}
