package com.example.imdbclone

import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.DataClasses.ShowResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val retrofit = Retrofit.Builder().baseUrl("https://streaming-availability.p.rapidapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor("add98da3c0msh3d981b1640d6b8cp1048e8jsn09ceaab7974c"))
            .build()
    ).build()

val imdbService = retrofit.create(ApiService::class.java)

interface ApiService {

    @GET("shows/top")
    suspend fun getShows(
        @Query("country") country: String,
        @Query("service") service: String,
        @Query("show_type") showType: String
    ): List<ShowDetails>

    @GET("shows/search/filters")
    suspend fun getFilteredShows(
        @Query("country") country: String,
        @Query("service") service: String,
        @Query("catalogs") catalogs: String,
        @Query("show_type") showType: String,
        @Query("genres") genres: String,
        @Query("rating_min") rating_min: Int,
        @Query("order_by") order_by: Int,

    ): List<ShowDetails>




}