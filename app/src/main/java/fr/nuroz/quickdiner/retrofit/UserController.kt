package fr.nuroz.quickdiner.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface UserController {
    @GET("user/types")
    suspend fun getTypes(): Response<List<String>>
}