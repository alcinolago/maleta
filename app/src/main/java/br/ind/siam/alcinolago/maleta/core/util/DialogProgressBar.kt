package br.ind.siam.alcinolago.maleta.core.util

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import br.ind.siam.alcinolago.maleta.R

class DialogProgressBar {

    companion object {

        private lateinit var dialog: Dialog

        fun show(context: Context) {

            dialog = AlertDialog.Builder(context)
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
                .show()

            dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }

        fun dismiss() {
            dialog.dismiss()
        }
    }
}
