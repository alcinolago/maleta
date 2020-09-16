package br.ind.siam.alcinolago.maleta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.ind.siam.alcinolago.maleta.data.entities.BookingEntity
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface BookingDao {

    @Query("SELECT * FROM booking")
    fun all(): Observable<List<BookingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(loginEntity: BookingEntity): Maybe<Long>
}
