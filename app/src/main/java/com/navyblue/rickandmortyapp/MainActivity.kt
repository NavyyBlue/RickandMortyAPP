package com.navyblue.rickandmortyapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView

class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.characterByIdLiveData.observe(this) { response ->

            epoxyController.characterResponse = response
            if (response == null) {
                Toast.makeText(this@MainActivity, "Error network call", Toast.LENGTH_SHORT).show()
                return@observe
            }

        }
        val id = intent.getIntExtra(Constants.INTENT_CHARACTER_ID, 1)
        viewModel.refreshCharacter(id)

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

}