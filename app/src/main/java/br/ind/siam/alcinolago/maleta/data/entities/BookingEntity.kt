package br.ind.siam.alcinolago.maleta.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booking")
class BookingEntity {

    @PrimaryKey(autoGenerate = true)
    var booking_id: Long? = null
    var booking_created_at: String? = null
    var booking_period: String? = null
    var booking_end: String? = null
    var booking_status: Int? = 0
}
