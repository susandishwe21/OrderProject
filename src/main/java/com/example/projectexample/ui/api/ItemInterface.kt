package com.example.projectexample.ui.api

import com.example.projectexample.ui.model.Item
import com.example.projectexample.ui.model.ItemX
import retrofit2.Call
import retrofit2.http.GET

interface ItemInterface {
    @GET("item")
    fun getItem(): Call<Item>
}