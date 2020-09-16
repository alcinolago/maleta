package br.ind.siam.alcinolago.maleta.presentation.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ind.siam.alcinolago.maleta.Constants.USER_CREATED
import br.ind.siam.alcinolago.maleta.R
import br.ind.siam.alcinolago.maleta.core.extension.mutableLiveData
import br.ind.siam.alcinolago.maleta.core.extension.rx.observe
import br.ind.siam.alcinolago.maleta.core.listener.Event
import br.ind.siam.alcinolago.maleta.core.preference.PreferenceRepository
import br.ind.siam.alcinolago.maleta.data.entities.LoginEntity
import br.ind.siam.alcinolago.maleta.domain.interactor.login.LoginInteractor
import br.ind.siam.alcinolago.maleta.provider.string.StringProvider
import br.ind.siam.alcinolago.maleta.rx.DefaultCompletableObserver
import br.ind.siam.alcinolago.maleta.rx.DefaultSingleObserver

class LoginViewModel(
    private val loginInteractor: LoginInteractor,
    private val preferenceRepository: PreferenceRepository,
    private val stringProvider: StringProvider,
) : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val emailErrorMessage = MutableLiveData<Event<String>>()
    val passwordErrorMessage = MutableLiveData<Event<String>>()
    val loginOnSuccessObservable: MutableLiveData<LoginEntity> = MutableLiveData()
    val loginOnErrorObservable: MutableLiveData<String> = MutableLiveData()
    val isHideKeyBoard = mutableLiveData(Event(false))
    val isLoading = MutableLiveData<Boolean>()

    private fun generateFirstAccessUserData() {

        val loginEntity = LoginEntity()
        loginEntity.let {
            it.user_email = "usuario@siam.ind.br"
            it.user_password = "123456"
        }
        loginInteractor.generateUserData(loginEntity)
            .observe(object : DefaultCompletableObserver() {
                override fun onComplete() {
                    super.onComplete()
                    preferenceRepository.preventRepeatData = 1
                    Log.d(USER_CREATED, stringProvider.getString(R.string.user_default))
                }
            })
    }

    fun checkIsFirstAccess() {
        if (!preferenceRepository.userIsFirstAccess && preferenceRepository.preventRepeatData != 1)
            generateFirstAccessUserData()
    }

    fun validateDataEntry() {

        isLoading.value = true

        val emailInput = email.value
        var passwordInput = password.value

        if (emailInput?.trim().isNullOrEmpty()) {
            isLoading.value = false
            emailErrorMessage.value = Event(stringProvider.getString(R.string.error_input_login))
        }

        if (passwordInput?.trim().isNullOrEmpty()) {
            isLoading.value = false
            passwordErrorMessage.value =
                Event(stringProvider.getString(R.string.error_input_password))
        }

        if (!email.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
            doLogin()
        }
    }

    fun doLogin() {

        isHideKeyBoard.value = Event(true)

        loginInteractor.login(email.value.toString(), password.value.toString())
            .observe(object : DefaultSingleObserver<LoginEntity>() {
                override fun onSuccess(loginData: LoginEntity) {
                    isLoading.value = false

                    if (loginData.user_email.isNullOrEmpty()) {
                        loginOnErrorObservable.value = "Login ou senha inválidos!"
                    } else {
                        preferenceRepository.userIsFirstAccess = true
                        loginOnSuccessObservable.value = loginData
                    }
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    isLoading.value = false
                    loginOnErrorObservable.value = stringProvider.getString(R.string.user_not_found)
                }
            })
    }
}