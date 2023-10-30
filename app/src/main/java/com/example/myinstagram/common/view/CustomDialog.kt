package com.example.myinstagram.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myinstagram.databinding.DialogCustomBinding

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCustomBinding

    private lateinit var txtButtons: Array<TextView>

    private var titleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setTitle(titleId: Int) {
        this.titleId = titleId
    }

    override fun show() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.show()

        titleId?.let {
            binding.txtDialogTitle.setText(it)
        }

        for (textView in txtButtons){
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(30,50,30,50)

            binding.dialogContainer.addView(textView, layoutParams)
        }
    }

    fun addButton(vararg texts: Int, listener: View.OnClickListener){
        txtButtons = Array(texts.size){
            TextView(context)
        }

        texts.forEachIndexed { index, i ->
            txtButtons[index].id = i
            txtButtons[index].setText(i)
            txtButtons[index].setOnClickListener {
                listener.onClick(it)
                dismiss()
            }
        }
    }

}