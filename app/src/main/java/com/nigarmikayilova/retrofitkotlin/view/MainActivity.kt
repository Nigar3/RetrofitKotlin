package com.nigarmikayilova.retrofitkotlin.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nigarmikayilova.retrofitkotlin.R
import com.nigarmikayilova.retrofitkotlin.adapter.retrofitAdapter
import com.nigarmikayilova.retrofitkotlin.databinding.ActivityMainBinding
import com.nigarmikayilova.retrofitkotlin.model.CryptoModel
import com.nigarmikayilova.retrofitkotlin.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), retrofitAdapter.Listener {

    private val BASE_URL="https://raw.githubusercontent.com"
    private var cryptoModels:ArrayList<CryptoModel>?=null
    private lateinit var binding: ActivityMainBinding
    private var retrofitAdapter:retrofitAdapter?=null


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        //recyclerView
        val layoutManager: RecyclerView.LayoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager




            loadData()
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        //https://api.nomics.com/v1/prices?key=2187154b76945f2373394aa34f7dc98a
        //2187154b76945f2373394aa34f7dc



    }

    private fun loadData(){
        //retrofit object created
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //connect retrofit with API
        val service= retrofit.create(CryptoAPI::class.java)
        val call= service.getData()

        call.enqueue(object :Callback<List<CryptoModel>>{
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
            if (response.isSuccessful){
                response.body()?.let {
                    cryptoModels= ArrayList(it)
                      cryptoModels?.let {
                          retrofitAdapter = retrofitAdapter(it , this@MainActivity)
                          binding.recyclerView.adapter=retrofitAdapter
                      }
                    for(cryptoModel:CryptoModel in cryptoModels!!){
                        println(cryptoModel.currency)
                        println(cryptoModel.price)

                    }
                }

    }
}

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG ).show()
    }


}