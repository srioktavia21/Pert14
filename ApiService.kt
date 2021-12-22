package com.informatika.databarang.network

import com.informatika.databarang.model.ResponseActionBarang
import com.informatika.databarang.model.ResponseBarang
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET(value = "read.php")
    fun getBarang() : Call<ResponseBarang>

    @FormUrlEncoded
    @POST("create.php")
    fun insertBarang(
            @Field("Nama_Barang") namaBarang: String?,
            @Field("Jumlah_Barang") jmlBarang: String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
            @Field("id")  id:String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
            @Field("id") id:String?
    ): Call<ResponseActionBarang>
}