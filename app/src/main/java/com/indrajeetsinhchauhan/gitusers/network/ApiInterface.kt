package com.indrajeetsinhchauhan.gitusers.network

import com.indrajeetsinhchauhan.gitusers.data.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/users")
    fun fetchAllUsers(): Call<List<UserModel>>

    @GET("/users/{user}")
    fun fetchUser(@Path("user") user:String): Call<UserModel>
}