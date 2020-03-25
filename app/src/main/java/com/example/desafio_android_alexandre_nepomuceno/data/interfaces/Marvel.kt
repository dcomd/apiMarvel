package com.example.desafio_android_alexandre_nepomuceno.data.interfaces


import com.example.desafio_android_alexandre_nepomuceno.data.response.ComicDataWrapper
import com.example.desafio_android_alexandre_nepomuceno.data.response.MarvelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


interface Marvel {

    companion object {
        const val PUBLIC_KEY = "e5ebe0a9fd6c82a46b98ccf280a83bf1"
        const val PRIVATE_KEY = "1acf92f75b12677709ea53e4974933b305a34ae5"
        val TS = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
    }


    @GET("/v1/public/characters")
    fun loadCharacters(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("hash") hash: String = getMd5(TS),
        @Query("ts") ts: String = TS
    ): Call<MarvelResponse>

    @GET("v1/public/characters/{characterId}/comics")
    fun loadCharacter(
        @Path("characterId") id: String
        , @Query("limit") limit: Int = 1
        , @Query("offset") offset: Int = 0
        , @Query("ts") ts: String = TS
        , @Query("apikey") apikey: String = PUBLIC_KEY
        , @Query("hash") hash: String = getMd5(TS)

    ): Call<ComicDataWrapper>


    private fun getMd5(ts: String): String {
        try {

            val md = MessageDigest.getInstance("MD5")

            val messageDigest = md.digest(
                ts.toByteArray()
                        + PRIVATE_KEY.toByteArray()
                        + PUBLIC_KEY.toByteArray()
            )

            val no = BigInteger(1, messageDigest)

            var hashtext = no.toString(16)
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }


}
