package com.informatika.databarang

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika.databarang.adapter.ListContent
import com.informatika.databarang.model.ResponseActionBarang
import com.informatika.databarang.model.ResponseBarang
import com.informatika.databarang.network.Koneksi
import kotlinx.android.synthetic.main_activity_insert_data.*
import kotlinx.android.synthetic.main_activity_insert_data.rv_data_barang
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val etNamaBarang = et_nama_barang.text
            val etJmlBarang = et_jumlah_barang.text
            if (etJmlBarang.isEmpty()) {
                Toast.makeText(this@InsertDataActivity, "Jumlah Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            } else if (etNamaBarang.isEmpty()) {
                Toast.makeText(this@InsertDataActivity, "Nama Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            } else {
                actionData(etNamaBarang.toString(), etJmlBarang.toString())
            }
        }

        btn_clear.setOnClickListner {
            formClear()
        }

        getData()

    }
    fun formClear() {
        et_nama_barang.text.clear()
        et_jumlah_barang.text.clear()

    }
    fun actionData(namaBarang: String, jmlBarang: String) {
        Koneksi.service . insertBarang (namaBarang, jmlBarang).enqueque(object : Callback<ResponseActionBarang> {
            override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                Log.d("pesan1, t.localizedMessage")
            }

            override fun onResponse(
                    call: Call<ResponseActionBarang>,
                    response: Response<ResponseActionBarang>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this @insertDataActivity, "data berhasil disimpan", Toast.LENGTH_LONG).show()
                    formclear()
                    getData()
                }
            }
        })
    }
    fun getData() {
        Koneksi.service. getBarang ().enqueque(object : Callback<ResponseBarang> {
            override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                Log.d("pesan1, t.localizedMessage")
            }

            override fu onResponse(
                call: Call <ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity)


                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }
}
