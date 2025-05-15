package com.example.groceryqrscanner

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface GroceryApiService {
    @GET
    suspend fun getGroceryInfo(@Url url: String): Response<GroceryInfo>
}

class GroceryRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/") // This is a placeholder, URL will be provided in each request
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        
    private val apiService = retrofit.create(GroceryApiService::class.java)
    
    suspend fun getGroceryInfoFromUrl(url: String): Response<GroceryInfo> {
        return apiService.getGroceryInfo(url)
    }
}
