package br.ind.siam.alcinolago.maleta.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class LoginEntity {

    @PrimaryKey(autoGenerate = true)
    var user_id: Long? = null
    var user_email: String? = null
    var user_password: String? = null
}
