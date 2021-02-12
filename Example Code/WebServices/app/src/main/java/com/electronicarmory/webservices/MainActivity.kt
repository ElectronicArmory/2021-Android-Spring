package com.electronicarmory.webservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var currentPrice:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitcoinTextView = findViewById<TextView>(R.id.bitcoinTextView)

        bitcoinTextView.text = "$36,000.00"

        val serviceDownloader = BitcoinPriceService()

        val callback = object: Callback<BpiResponse> {
            override fun onFailure(call: Call<BpiResponse>?, t: Throwable?) {
                Log.e("MainActivity", "Problems", t)
            }

            override fun onResponse(call: Call<BpiResponse>?, response: Response<BpiResponse>?) {
                response?.isSuccessful.let{
                    Log.d("Response", response.toString())
                    Log.d("Response", response?.body().toString())
                    val resp = response?.body()?.bpi?.USD
                    this@MainActivity.currentPrice = resp?.rate ?: "Unknown"


                    resp?.symbol?.let {
                        it.length

                    }
                    bitcoinTextView.text = "$%.2f".format(resp?.rateFloat)
                }
            }

        }
        serviceDownloader.getBitcoinPrice(callback)

    }
}