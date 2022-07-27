package com.dieunn.lab4_androidnetworking

import retrofit2.Call
import retrofit2.http.*

interface GuestApi {
    @GET("/guest")
    suspend fun getGuestList():ApiRetrieved

    @GET("/guest/get/{guest_id}")
    fun getGuestWithId(
        @Path("guest_id") guest_id: String
    ) : Call<String>

//    @GET("/guest/add/")
//    fun addNewGuest(
//        @Query("first_name") first_name:String,
//        @Query("last_name") last_name:String,
//        @Query("email") email:String,
//    ):Call<String>

    @GET("guest/edit/{guest_id}")
    fun editGuest(
        @Path("guest_id") guest_id:String,
        @Query("first_name") first_name: String,
        @Query("last_name") last_name:String,
        @Query("email") email:String,
    ) : Call<String>

    @GET("guest/delete/{guest_id}")
    fun deleteGuest(
        @Path("guest_id") guest_id: String
    ) : Call<String>

    @GET("guest/edit/{guest_id}")
    fun updateGuest(
        @Path("guest_id") guest_id: String,
        @Query("first_name") first_name: String,
        @Query("last_name") last_name:String,
        @Query("email") email:String,
    ) : Call<String>


}