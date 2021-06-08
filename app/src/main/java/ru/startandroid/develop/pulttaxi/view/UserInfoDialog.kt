package ru.startandroid.develop.pulttaxi.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import ru.startandroid.develop.pulttaxi.databinding.UserInfoDialogBinding

class UserInfoDialog {
    companion object {
        @SuppressLint("InflateParams")
        fun userInfoDialog(
            context: Context,
            userName: String,
            userId: Int,
            userEmail: String
        ): Dialog {
            val dialog = Dialog(context)
            val binding = UserInfoDialogBinding.inflate(LayoutInflater.from(context))
            val view = binding.root
            dialog.setContentView(view)
            with(binding) {
                userEmailTextView.text = userEmail
                userIdTextView.text = userId.toString()
                userNameTextView.text = userName
            }
            return dialog
        }
    }
}