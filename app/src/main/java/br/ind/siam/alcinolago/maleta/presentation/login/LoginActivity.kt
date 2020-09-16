package br.ind.siam.alcinolago.maleta.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.ind.siam.alcinolago.maleta.R
import br.ind.siam.alcinolago.maleta.core.BaseActivity
import br.ind.siam.alcinolago.maleta.core.util.InputTextWatcher
import br.ind.siam.alcinolago.maleta.core.util.Util
import br.ind.siam.alcinolago.maleta.databinding.ActivityLoginBinding
import br.ind.siam.alcinolago.maleta.presentation.booking.list.ListBookingActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this

        checkLoggedin()
        initViews()
        observeIsShowLoading()
        observeIsHideKeyboard()
        observeLoginOnsuccess()
        observeLoginOnError()
        observerValidateDataEntry()
    }

    private fun checkLoggedin() {
        loginViewModel.checkIsFirstAccess()
    }

    override fun initViews() {
        editTextEmail!!.addTextChangedListener(InputTextWatcher(input_layout_email!!))
        editTextPassword!!.addTextChangedListener(InputTextWatcher(input_layout_password!!))
    }

    private fun observeIsShowLoading() {
        loginViewModel.isLoading.observe(this, { loading ->
            if (loading) showProgress() else hideProgress()
        })
    }

    private fun observeIsHideKeyboard() {

        loginViewModel.isHideKeyBoard.observe(this, {
            it?.getContentIfNotHandled()?.let { isHideKeyboard ->
                if (isHideKeyboard) Util.hideKeyboard(this)
            }
        })
    }

    private fun observerValidateDataEntry() {

        loginViewModel.emailErrorMessage.observe(this, {
            it?.getContentIfNotHandled()?.let { errorMessage ->
                input_layout_email.error = errorMessage
                editTextEmail.requestFocus()
            }
        })

        loginViewModel.passwordErrorMessage.observe(this, {
            it?.getContentIfNotHandled()?.let { errorMessage ->
                input_layout_password.error = errorMessage
                editTextPassword.requestFocus()
            }
        })
    }

    private fun observeLoginOnsuccess() {

        loginViewModel.loginOnSuccessObservable.observe(this, { loginDTO ->
            loginDTO?.let {
                hideProgress()
                navigateToMain()
            }
        })
    }

    private fun observeLoginOnError() {
        loginViewModel.loginOnErrorObservable.observe(this, {

            it?.let { it ->
                hideProgress()
                showMessage(this, resources.getString(R.string.alert_dialog_title), it)
            }
        })
    }

    private fun navigateToMain() {
        startActivity(Intent(this, ListBookingActivity::class.java))
    }
}
