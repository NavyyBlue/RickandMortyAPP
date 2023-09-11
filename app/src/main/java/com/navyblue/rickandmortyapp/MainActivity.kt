package com.navyblue.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headerImage = findViewById<AppCompatImageView>(R.id.HeaderImageView)
        val nameText = findViewById<AppCompatTextView>(R.id.nameTextView)
        val statusText = findViewById<AppCompatTextView>(R.id.statusTextView)
        val genderImage = findViewById<AppCompatImageView>(R.id.genderImageView)
        val originText = findViewById<AppCompatTextView>(R.id.originLabelTextView)
        val speciesText = findViewById<AppCompatTextView>(R.id.speciesLabelTextView)


        viewModel.refreshCharacter(54)
        viewModel.characterByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(this@MainActivity, "Error network call", Toast.LENGTH_SHORT).show()
                return@observe
            }

            nameText.text = response.name
            statusText.text = response.status
            originText.text = response.origin.name
            speciesText.text = response.species
            if (response.gender.equals("male", true))
                genderImage.setImageResource(R.drawable.ic_male)
            else genderImage.setImageResource(R.drawable.ic_female)
            Picasso.get().load(response.image).into(headerImage)

        }
    }
}