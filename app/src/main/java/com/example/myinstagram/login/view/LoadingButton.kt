package com.example.myinstagram.login.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.example.myinstagram.R

class LoadingButton: FrameLayout {

    private lateinit var button: Button
    private lateinit var progress: ProgressBar
    private var text: String? = null

    constructor(context:Context): super(context)
    constructor(context:Context, attrs: AttributeSet?): super(context, attrs){
        setup(context, attrs)
    }
    constructor(context:Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?){

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.button_loading, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)
        text = typedArray.getString(R.styleable.LoadingButton_text)

        button = getChildAt(0) as Button
        progress = getChildAt(1) as ProgressBar

        button.text = text
        button.isEnabled = false

        typedArray.recycle()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        button.isEnabled = enabled
    }

    override fun setOnClickListener(l: OnClickListener?) {
        button.setOnClickListener(l)
    }

    fun showProgress(enabled: Boolean){
        if (enabled){
            button.text = ""
            progress.visibility = View.VISIBLE
            button.isEnabled = false
        } else {
            button.text = text
            progress.visibility = View.GONE
            button.isEnabled = true
        }
    }

}