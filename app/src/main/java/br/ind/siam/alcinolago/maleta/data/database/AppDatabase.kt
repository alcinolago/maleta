package br.ind.siam.alcinolago.maleta.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.ind.siam.alcinolago.maleta.data.dao.BookingDao
import br.ind.siam.alcinolago.maleta.data.dao.LoginDao
import br.ind.siam.alcinolago.maleta.data.entities.BookingEntity
import br.ind.siam.alcinolago.maleta.data.entities.LoginEntity

@Database(entities = [
    LoginEntity::class,
    BookingEntity::class
],
    version = 1,
    exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun loginDao(): LoginDao
    abstract fun bookingDao(): BookingDao
}
