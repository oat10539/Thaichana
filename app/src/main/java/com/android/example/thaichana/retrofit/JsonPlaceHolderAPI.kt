package com.android.example.thaichana.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderAPI {


    @GET("{shopID}")
    fun getShopName(@Path(value = "shopID")shopID :String):Call<Shop>


}