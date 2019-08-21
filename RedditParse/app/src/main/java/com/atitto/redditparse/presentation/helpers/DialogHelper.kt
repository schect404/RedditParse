package com.atitto.redditparse.presentation.helpers

import android.app.AlertDialog
import android.content.Context

object DialogHelper {

    fun showErrorAlert(context: Context, error: String) {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(error)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

}