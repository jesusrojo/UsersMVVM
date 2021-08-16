package com.jesusrojo.usersmvvm.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users_table")
data class User(
    val userId: Int,
    val name: String,
    val userName: String,
    val email: String,
    val phone: String,
    val website: String,

    val address: String,
    val company: String

) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    override fun toString(): String {
        return "USER:" +
                "\n\tUSER-ID: $userId\n\tNAME: $name\n\tUSERNAME: $userName" +
                "\n\tEMAIL: $email\n\tPHONE: $phone\n\tWEBSITE: $website" +
                "\n\t$address\n\t$company"
    }
}