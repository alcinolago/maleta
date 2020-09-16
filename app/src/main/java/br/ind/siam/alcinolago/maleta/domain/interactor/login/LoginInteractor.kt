package br.ind.siam.alcinolago.maleta.domain.interactor.login

import br.ind.siam.alcinolago.maleta.data.entities.LoginEntity
import io.reactivex.Completable
import io.reactivex.Single

interface LoginInteractor {
    fun generateUserData(login: LoginEntity): Completable
    fun login(email: String, password: String): Single<LoginEntity>
}
