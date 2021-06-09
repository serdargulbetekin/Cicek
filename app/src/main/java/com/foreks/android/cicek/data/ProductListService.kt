package com.foreks.android.cicek.data


import okhttp3.ResponseBody
import retrofit2.http.GET

interface ProductListService {
    @GET(value = "product/ch/dynamicproductlist")
    suspend fun getProduct(): ResponseBody

}