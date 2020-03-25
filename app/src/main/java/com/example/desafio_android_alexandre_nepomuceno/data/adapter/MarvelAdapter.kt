package com.example.desafio_android_alexandre_nepomuceno.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_alexandre_nepomuceno.R
import com.example.desafio_android_alexandre_nepomuceno.data.model.CharacterModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_heroes.view.*

class MarvelCharacterAdapter(private val character: MutableList<CharacterModel>,
                             private val listener: AdapterListener):
    RecyclerView.Adapter<MarvelCharacterAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.list_heroes, parent, false)
        return ViewHolder(inflate)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(character[position])
    }

    override fun getItemCount() = character.size


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private lateinit var char: CharacterModel

        fun bind(result: CharacterModel){
            this.char = result

            Picasso.with(itemView.context)
                .load(result.thumbnail?.path + "." + result.thumbnail?.extension)
                .into(itemView.image_thumbnail)
            itemView.text_name.text = result.name

            itemView.image_thumbnail.setOnClickListener { listener.showDetails(char) }

        }

    }

    interface AdapterListener{
        fun showDetails(char: CharacterModel)
    }
}
