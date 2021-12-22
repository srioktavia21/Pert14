package com.informatika.databarang.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Koneksi {
    companion object {
        val BaseUrl = "https://192.168.163.36/dabar/api/"
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service: ApiService = retrofit.create(ApiService::class.java)
    }
}