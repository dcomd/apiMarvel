package com.example.desafio_android_alexandre_nepomuceno.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_alexandre_nepomuceno.data.model.CharacterModel
import com.example.desafio_android_alexandre_nepomuceno.data.model.ImageModel
import com.example.desafio_android_alexandre_nepomuceno.data.repository.MarvelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.await

class MarvelViewModel(val marvelRepository: MarvelRepository) : ViewModel() {

    val viewLiveDataList: MutableLiveData<MutableList<CharacterModel>> = MutableLiveData()
    private val errorGet: MutableLiveData<HttpException> = MutableLiveData()
    val viewLiveDataErrorGet: LiveData<HttpException> = errorGet
    val viewLiveDataImage: MutableLiveData<String> = MutableLiveData()


    fun getSelect() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retorno = marvelRepository.execute().await()
                var marvelList = mutableListOf<CharacterModel>()

                retorno.data.results?.let {
                    for (item in it) {
                        var model = CharacterModel(
                            id = item.id,
                            name = item.name,
                            description = item.description,
                            thumbnail = item.thumbnail?.let { it ->
                                ImageModel(
                                    path = it.path,
                                    extension = it.extension
                                )
                            }
                        )
                        marvelList.add(model)


                    }
                    viewLiveDataList.postValue(marvelList)

                }


            } catch (e: HttpException) {
                errorGet.postValue(e)
            }
        }

    }

    fun getMagazine(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retorno = marvelRepository.executebyId(id).await()

                retorno.data.results?.let {
                    for (item in it) {
                        viewLiveDataImage.postValue(item.thumbnail?.path + "." + item.thumbnail?.extension)
                    }

                }

            } catch (e: HttpException) {
                errorGet.postValue(e)
            }
        }

    }


}