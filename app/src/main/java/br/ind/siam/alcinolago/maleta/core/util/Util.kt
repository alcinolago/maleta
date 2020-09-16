package br.ind.siam.alcinolago.maleta.core.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog

object Util {

    fun showAlertDialog(context: Context, title: String, message: String) {

        val alertDialog = AlertDialog.Builder(context).create()

        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK") { _, _ -> alertDialog.dismiss() }

        alertDialog.show()
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}