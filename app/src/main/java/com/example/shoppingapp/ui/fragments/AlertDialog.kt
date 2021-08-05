package com.example.shoppingapp.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.shoppingapp.viewmodels.LikeViewModel

class AlertDialog : DialogFragment() {

    private val likeViewModel : LikeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {

            val builder = AlertDialog.Builder(it)
            builder.setMessage("선택 항목을 찜 항목에서 삭제하시겠습니까?")
                    .setPositiveButton("삭제",
                            DialogInterface.OnClickListener { _, _ ->
                                likeViewModel.deleteStuff(likeViewModel.likeStuff.value!!)
                            })
                    .setNegativeButton("취소",
                            DialogInterface.OnClickListener{ _, _ -> })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}