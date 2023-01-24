package com.kplptik.networks


import com.kplptik.APIdatamodels.MatkulDiampuModel.MatkulDiampuResponse
import com.kplptik.APIdatamodels.ProfilDosenModel.ProfilDosenResponse
import com.kplptik.APIdatamodels.ProfilMahasiswaModel.ProfilMahasiswaResponse
import com.kplptik.APIdatamodels.authentication.LoginResponse
import com.kplptik.APIdatamodels.authentication.LogoutResponse
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

    @FormUrlEncoded
    @POST("/api/logout")
    fun logout(
        @Field("Authorization") token: String
    ): Call<LogoutResponse>

    @GET("/api/list-matkul/{nip-dosen}")
    fun listmatkuldiampudosen(
        @Header("Authorization") token: String,
        @Path("nip-dosen") nip: String
    ): Call<MatkulDiampuResponse>

    @GET("/api/profil-dosen")
    fun profildosen(
        @Header("Authorization") token: String
    ): Call<ProfilDosenResponse>

    @GET("/api/mahasiswa")
    fun profilmahasiswa(
        @Header("Authorization") token: String
    ): Call<ProfilMahasiswaResponse>
}