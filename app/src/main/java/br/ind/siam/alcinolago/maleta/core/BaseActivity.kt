package br.ind.siam.alcinolago.maleta.core

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import br.ind.siam.alcinolago.maleta.core.util.DialogProgressBar
import br.ind.siam.alcinolago.maleta.core.util.Util

open class BaseActivity : AppCompatActivity() {

    open fun initViews() {}

    open fun observeViewModel() {}

    open fun setupToolbar(title: String) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    open fun configureRecyclerView() {}

    open fun configureAdapterRecyclerView() {}

    /* BASE ACTIVITY FUNCTIONS*/
    fun showProgress() {
        DialogProgressBar.show(this)
    }

    fun hideProgress() {
        DialogProgressBar.dismiss()
    }

    fun showMessage(context: Context, title: String, message: String) {
        Util.showAlertDialog(context, title, message)
    }
}