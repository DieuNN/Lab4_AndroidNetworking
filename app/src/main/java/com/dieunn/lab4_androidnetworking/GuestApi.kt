package com.dieunn.lab4_androidnetworking

import retrofit2.Call
import retrofit2.http.*

interface GuestApi {
    @GET("/guest")
    suspend fun getGuestList():ApiRetrieved

    @GET("/guest/add/")
    fun addNewGuest(
        @Query("first_name") first_name:String,
        @Query("last_name") last_name:String,
        @Query("email") email:String,
    ):Call<String>
}