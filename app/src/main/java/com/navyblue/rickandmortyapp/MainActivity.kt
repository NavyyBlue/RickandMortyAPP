package com.navyblue.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headerImage = findViewById<AppCompatImageView>(R.id.HeaderImageView)
        val nameText = findViewById<AppCompatTextView>(R.id.nameTextView)
        val statusText = findViewById<AppCompatTextView>(R.id.statusTextView)
        val genderImage = findViewById<AppCompatImageView>(R.id.genderImageView)
        val originText = findViewById<AppCompatTextView>(R.id.originLabelTextView)
        val speciesText = findViewById<AppCompatTextView>(R.id.speciesLabelTextView)

        rickAndMortyService.getCharacterById(54)
            .enqueue(object : Callback<GetCharacterByIdResponse> {
                override fun onResponse(
                    call: Call<GetCharacterByIdResponse>,
                    response: Response<GetCharacterByIdResponse>
                ) {
                    Log.i("MainActivity", response.toString())
                    if (!response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Error network call", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    val body = response.body()!!
                    nameText.text = body.name
                    statusText.text = body.status
                    originText.text = body.origin.name
                    speciesText.text = body.species
                    if (body.gender.equals("male", true))
                        genderImage.setImageResource(R.drawable.ic_male)
                    else genderImage.setImageResource(R.drawable.ic_female)
                    Picasso.get().load(body.image).into(headerImage)

                }

                override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                    Log.i("MainActivity02", t.message ?: "Null message")
                }
            })
    }
}