package br.ind.siam.alcinolago.maleta.core.extension

import android.view.View
import br.ind.siam.alcinolago.maleta.core.listener.SafeClickListener

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}