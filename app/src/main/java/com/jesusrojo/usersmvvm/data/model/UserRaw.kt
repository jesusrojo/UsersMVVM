package com.jesusrojo.usersmvvm.data.model

import com.google.gson.annotations.SerializedName

data class UserRaw(

        @SerializedName("id") val userId: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("username") val userName: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("phone") val phone: String?,
        @SerializedName("website") val website: String?,

        @SerializedName("address") val address: Address?,
        @SerializedName("company") val company: Company?
)