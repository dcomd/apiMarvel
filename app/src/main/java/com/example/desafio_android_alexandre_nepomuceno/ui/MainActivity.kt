package com.example.desafio_android_alexandre_nepomuceno.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.desafio_android_alexandre_nepomuceno.R
import com.example.desafio_android_alexandre_nepomuceno.data.adapter.MarvelCharacterAdapter
import com.example.desafio_android_alexandre_nepomuceno.data.model.CharacterModel
import com.example.desafio_android_alexandre_nepomuceno.ui.viewModel.MarvelViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), MarvelCharacterAdapter.AdapterListener {

    val marvelViewModel: MarvelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolBarMain.setTitle("Marvel List")
        setSupportActionBar(toolBarMain)

        bindFunctios()
        observables()
    }

    private fun bindFunctios() {
        repository_progress.visibility = View.VISIBLE
        marvelViewModel.getSelect()
    }


    private fun observables() {
        marvelViewModel.viewLiveDataList.observe(this, Observer {

            it?.let { marvel ->

                with(recicleMarvel) {
                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                        this@MainActivity,
                        androidx.recyclerview.widget.RecyclerView.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)
                    adapter = MarvelCharacterAdapter(marvel, this@MainActivity)
                    repository_progress.visibility = View.GONE
                }
            } ?: run {
                Toast.makeText(this, getString(R.string.erro_post_value), Toast.LENGTH_LONG).show()
                repository_progress.visibility = View.GONE
            }

        })

        marvelViewModel.viewLiveDataErrorGet.observe(this, Observer {
            Toast.makeText(this, getString(R.string.erro_post_value), Toast.LENGTH_LONG).show()
            repository_progress.visibility = View.GONE
        })
    }

    override fun showDetails(char: CharacterModel) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", char.id.toString())
        intent.putExtra("image", char.thumbnail?.path + "." + char.thumbnail?.extension)
        intent.putExtra("name", char.name)
        intent.putExtra("description", char.description)
        startActivity(intent)

    }

}
