package com.example.desafio_android_alexandre_nepomuceno.di

import com.example.desafio_android_alexandre_nepomuceno.data.RetrofitInstanceMarvel
import com.example.desafio_android_alexandre_nepomuceno.data.repository.MarvelRepository
import com.example.desafio_android_alexandre_nepomuceno.ui.viewModel.MarvelViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { MarvelRepository() }
    viewModel { MarvelViewModel(get()) }
    single { RetrofitInstanceMarvel }
}