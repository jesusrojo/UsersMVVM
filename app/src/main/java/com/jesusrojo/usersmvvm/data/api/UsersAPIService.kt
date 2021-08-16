package com.jesusrojo.usersmvvm.data.api

import com.jesusrojo.usersmvvm.data.model.UserRaw
import retrofit2.Response
import retrofit2.http.GET

interface UsersAPIService {

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("users")
    suspend fun fetchUsers():  Response<List<UserRaw>>
}