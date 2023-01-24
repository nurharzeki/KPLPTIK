package com.kplptik.networks


import com.kplptik.APIdatamodels.MatkulDiampuModel.MatkulDiampuResponse
import com.kplptik.APIdatamodels.ProfilDosenModel.ProfilDosenResponse
import com.kplptik.APIdatamodels.authentication.LoginResponse
import com.kplptik.APIdatamodels.authentication.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface MainInterface {

    @FormUrlEncoded
    @POST("/api/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password:String
    ): Call<LoginResponse>

    @GET("/api/user")
    fun userCek(
        @Header("Authorization") token: String,
    ): Call<UserResponse>

    @GET("/api/list-matkul/{nip-dosen}")
    fun listmatkuldiampudosen(
        @Header("Authorization") token: String,
        @Path("nip-dosen") nip: String
    ): Call<MatkulDiampuResponse>

    @GET("/api/profil-dosen/{nip-dosen}")
    fun profildosen(
        @Header("Authorization") token: String,
        @Path("nip-dosen") nip: String
    ): Call<ProfilDosenResponse>
}