package com.nigarmikayilova.retrofitkotlin.service

import com.nigarmikayilova.retrofitkotlin.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //GET, POST, UPDATE, DELETE
    // https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json
    //https://api.nomics.com/v1/
    // prices?key=2187154b76945f2373394aa34f7dc98a

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    //call-> retrofit olani sec
    // call internetden asinxron sekilde veri endirmek ucun bir emeliyyat
    fun getData(): Call<List<CryptoModel>>

}