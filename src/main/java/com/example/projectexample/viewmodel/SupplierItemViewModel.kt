package com.example.projectexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectexample.ui.api.ItemApi
import com.example.projectexample.ui.model.Item
import com.example.projectexample.ui.model.ItemX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupplierItemViewModel : ViewModel() {

    var loading : MutableLiveData<Boolean> = MutableLiveData()
    var supplierItem : MutableLiveData<List<ItemX>> = MutableLiveData()
    var loadError : MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> =loading
    fun getItem() : LiveData<List<ItemX>> = supplierItem
    fun getLoadError() : LiveData<Boolean> = loadError

    private var itemApi : ItemApi = ItemApi()

    fun loadDetail(){
        loading.value =true
        var apiCallSupplierDetail =itemApi.getItem()
        apiCallSupplierDetail.enqueue(object : Callback<Item>{
            override fun onFailure(call: Call<Item>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                val supplierDetailList : List<ItemX> = response.body()?.items ?: emptyList()
                supplierItem.value = supplierDetailList

                Log.d("Detail" , response.body().toString())
            }

        })

    }

}