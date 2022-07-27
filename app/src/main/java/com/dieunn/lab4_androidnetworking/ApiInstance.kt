package com.dieunn.lab4_androidnetworking

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiInstance {
//   private val gson = GsonBuilder()
//        .setLenient().create()

    val api:GuestApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://apiandroidnetworkingdieunn.herokuapp.com")
            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GuestApi::class.java)
    }
}