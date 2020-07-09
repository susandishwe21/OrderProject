package com.example.projectexample.ui.api

import com.example.projectexample.ui.model.Item
import com.example.projectexample.ui.model.ItemX
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemApi {
    private val itemInterface : ItemInterface

    companion object {
        const val BASE_URL = "https://order.khaingthinkyi.me/api/setup/"
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        itemInterface = retrofit.create(ItemInterface::class.java)
    }

    fun getItem() : Call<Item> = itemInterface.getItem()
}