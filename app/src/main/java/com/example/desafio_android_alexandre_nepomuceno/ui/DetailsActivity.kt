package com.example.desafio_android_alexandre_nepomuceno.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.desafio_android_alexandre_nepomuceno.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.toolBarMain

class DetailsActivity : AppCompatActivity(){

    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        toolBarMain.setTitle("Marvel Details")
        setSupportActionBar(toolBarMain)
        getInformation()


        btnSendId.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                callIntent()
            }
        })
    }


    fun getInformation() {

        id = intent.getStringExtra("id")
        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")

        Picasso.with(this)
            .load(image)
            .into(imageHeroe)

        txtName.text = name.toString()
        txtDescription.text = description.toString()
    }

    private fun callIntent()
    {
        val intent = Intent(this, MagazineActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}



