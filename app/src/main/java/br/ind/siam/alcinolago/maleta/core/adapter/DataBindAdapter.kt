package br.ind.siam.alcinolago.maleta.core.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import br.ind.siam.alcinolago.maleta.core.extension.setSafeOnClickListener

object DataBindingAdapter {

    @BindingAdapter("bind:booleanVisibility")
    @JvmStatic
    fun setVisibility(view: View, visibility: Boolean) {
        view.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    @BindingAdapter("app:onSafeClick")
    @JvmStatic
    fun setSafeClickListener(view: View, clickListener: View.OnClickListener) {
        view.setSafeOnClickListener { clickListener.onClick(view) }
    }
}
