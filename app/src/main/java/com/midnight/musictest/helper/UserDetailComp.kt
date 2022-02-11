package com.midnight.musictest.helper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.midnight.musictest.R

class UserDetailComp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.user_detail, this)

        val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.UserStatus, 0, 0)

        val name = findViewById<TextView>(R.id.text_view_user_name)
        val scores = findViewById<TextView>(R.id.text_view_user_score)

        try {
            name.text = customAttributesStyle.getString(R.styleable.UserStatus_userName)
            scores.text = customAttributesStyle.getString(R.styleable.UserStatus_userScore)
            name.setTextColor((customAttributesStyle.getColor(R.styleable.UserStatus_textC,0)))
        } finally {
            customAttributesStyle.recycle()
        }
    }
}