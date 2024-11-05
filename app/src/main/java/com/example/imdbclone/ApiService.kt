package com.example.imdbclone

import android.media.tv.TvContract.Programs.Genres
import com.example.imdbclone.DataClasses.Show
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
            .addInterceptor(ApiKeyInterceptor("9d9cce55e5msh7f727307af75176p144f87jsne5449e289793"))
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


    @GET("shows/")
    suspend fun searchShow(
        @Query("id") id: String,

    ): List<ShowDetails>

    @GET("shows/search/filters")
    suspend fun getFilteredShows(
        @Query("country") country: String,
        @Query("service") service: String,
        @Query("catalogs") catalogs: String,
        @Query("show_type") showType: String,
        @Query("rating_min") rating_min: Int,
        @Query("genres") genre: String

    ): Show


@GET("shows/search/title")
    suspend fun searchShow(
        @Query("country") country: String,
        @Query("title") title: String
    ): List<ShowDetails>




}