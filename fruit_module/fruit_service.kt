package com.example.androiddev.fruit_module

import com.example.androiddev.book_module.BookModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://limkemhout8989.github.io"

interface APIService{
    @GET("MyApi/fruit.json")
    suspend fun getFruits(): List<FruitModel>

//    @GET("MyApi/fruit.json")
//    suspend fun searchFruits(
//        @Query("name") name: String = "",
//    ): List<FruitModel>

    companion object {
        var service : APIService? = null
        fun getInstance(): APIService{
            if(service == null){
                // Create a Gson instance with lenient parsing
                val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()

                // Initialize Retrofit with the custom Gson instance
                service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(APIService::class.java)
            }
            return service!!
        }
    }
}

interface SearchService{
    @GET("MyApi/{name}.json")
    suspend fun getsearchFruits(
        @Path("name") name: String = "",
    ): List<FruitModel>
    companion object {
        private var searchservice : SearchService? = null
        fun getInstance(): SearchService{
            if(searchservice == null){
                // Create a Gson instance with lenient parsing
                val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()

                // Initialize Retrofit with the custom Gson instance
                searchservice = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(SearchService::class.java)
            }
            return searchservice!!
        }
    }
}