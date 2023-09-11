package com.navyblue.rickandmortyapp

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import androidx.palette.graphics.Palette
import com.squareup.picasso.Callback
import java.lang.Exception

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
        val mainView = findViewById<ConstraintLayout>(R.id.MainView)


        viewModel.refreshCharacter(6)
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

            Picasso.get().load(response.image)
                .into(headerImage, object : Callback {
                    override fun onSuccess() {
                        val bitmap = (headerImage.drawable as BitmapDrawable).bitmap
                        val palette = Palette.from(bitmap).generate()
                        val dominantColor = palette.getDominantColor(0x00FFFFFF)

                        mainView.setBackgroundColor(dominantColor)
                        window.statusBarColor = dominantColor
                    }

                    override fun onError(e: Exception?) {
                        Toast.makeText(this@MainActivity, "Error network call", Toast.LENGTH_SHORT)
                            .show()
                    }
                })

        }
    }


}