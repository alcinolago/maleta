package br.ind.siam.alcinolago.maleta.domain.interactor.login

import br.ind.siam.alcinolago.maleta.data.database.AppDatabase
import br.ind.siam.alcinolago.maleta.data.entities.LoginEntity
import io.reactivex.Completable
import io.reactivex.Single

class LoginInteractorImpl(private val db: AppDatabase) : LoginInteractor {
    override fun generateUserData(login: LoginEntity): Completable {
        return db.loginDao().add(login)
    }

    override fun login(email: String, password: String): Single<LoginEntity> {
        return db.loginDao().getUser(email, password)
    }
}