package com.example.desafio_android_alexandre_nepomuceno.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.desafio_android_alexandre_nepomuceno.R
import com.example.desafio_android_alexandre_nepomuceno.data.adapter.MarvelCharacterAdapter
import com.example.desafio_android_alexandre_nepomuceno.ui.viewModel.MarvelViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.toolBarMain
import kotlinx.android.synthetic.main.activity_magazine.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MagazineActivity : AppCompatActivity() {

    val marvelViewModel: MarvelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magazine)

        toolBarMain.setTitle("Magazine")
        setSupportActionBar(toolBarMain)
        loadMagazine()
        loadImgageMagazine()
    }


    fun loadImgageMagazine() {
        marvelViewModel.viewLiveDataImage.observe(this, Observer {
            Picasso.with(this)
                .load(it)
                .into(imageMagazine)
        })

        marvelViewModel.viewLiveDataErrorGet.observe(this, Observer {
            Toast.makeText(this, getString(R.string.erro_post_value), Toast.LENGTH_LONG).show()
        })

    }

    fun loadMagazine() {
        val id = intent.getStringExtra("id")
        marvelViewModel.getMagazine(id)
    }
}
