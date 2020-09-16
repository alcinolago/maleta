package br.ind.siam.alcinolago.maleta.presentation.booking.list

import androidx.lifecycle.ViewModel
import br.ind.siam.alcinolago.maleta.core.extension.mutableLiveData
import br.ind.siam.alcinolago.maleta.core.listener.Event
import br.ind.siam.alcinolago.maleta.core.preference.PreferenceRepository

class ListBookingViewModel(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    val isLoggedIn = mutableLiveData(Event(false))
    val isLogout = mutableLiveData(Event(false))

    fun checkIsFirstAccess() {
        if (preferenceRepository.userIsFirstAccess) {
            isLoggedIn.value = Event(true)
        }
    }

    fun logout() {
        preferenceRepository.cleanAll()
        isLogout.value = Event(true)
    }
}