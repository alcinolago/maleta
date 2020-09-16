package br.ind.siam.alcinolago.maleta.data.dao

import androidx.room.*
import br.ind.siam.alcinolago.maleta.data.entities.LoginEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface LoginDao {

    @Query("SELECT * FROM user WHERE user_email = :email AND user_password = :password")
    fun getUser(email: String, password: String): Single<LoginEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(loginEntity: LoginEntity): Completable
}
