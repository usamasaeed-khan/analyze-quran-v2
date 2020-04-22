package com.example.analyzequranupdated

import retrofit2.Call
import retrofit2.http.*

interface APIService {

    //@FormUrlEncoded
    @GET("GetAllAlphabets")
    fun GetAllAlphabets():Call<ArrayList<String>>

    @GET("GetWordsFromRoot")
    fun GetWordsFromRoot(
        @Query("root")root:String
    ):Call<ArrayList<String>>


    @GET("GetWordsFromEntry")
    fun GetWordsFromEntry(
        @Query("root")root:String
    ):Call<ArrayList<String>>

    @GET("GetMeaningFromWord")
    fun GetMeaningFromWord(
        @Query("word")word:String
    ):Call<String>

}