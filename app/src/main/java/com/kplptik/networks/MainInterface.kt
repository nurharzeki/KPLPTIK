package com.kplptik.networks


import com.kplptik.APIdatamodels.DetailKhsMhs.DetailKhsMahasiswaResponse
import com.kplptik.APIdatamodels.DetailMatkulMahasiswaModel.DetailMatkulMahasiswaResponse
import com.kplptik.APIdatamodels.KhsMahasiswaModel.KhsMahasiswaResponse
import com.kplptik.APIdatamodels.KrsMahasiswaModel.KrsMahasiswaResponse
import com.kplptik.APIdatamodels.ListMahasiswaBimbinganModel.ListMahasiswaResponse
import com.kplptik.APIdatamodels.ListV.DetailMatkulResponse

import com.kplptik.APIdatamodels.ListV.ListMatkulDosenResponse
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

    @GET("/api/list-matkul")
    fun listmatkuldiampudosen(
        @Header("Authorization") token: String
    ): Call<ListMatkulDosenResponse>

    @GET("/api/detail-matkul/{id_matkul}")
    fun detailMatkuldosen(
        @Header("Authorization") token: String,
        @Path("id_matkul")idMatkul: Int
    ): Call<DetailMatkulResponse>

    @GET("/api/profil-dosen")
    fun profildosen(
        @Header("Authorization") token: String
    ): Call<ProfilDosenResponse>

    @GET("/api/mahasiswa")
    fun profilmahasiswa(
        @Header("Authorization") token: String
    ): Call<ProfilMahasiswaResponse>

    @GET("/api/listmahasiswa")
    fun listMahasiswaBimbingan(
        @Header("Authorization") token: String
    ): Call<ListMahasiswaResponse>

    @GET("/api/krs")
    fun listKrsMahasiswa(
        @Header("Authorization") token: String
    ): Call<KrsMahasiswaResponse>

    @GET("/api/khs")
    fun listKhsMahasiswa(
        @Header("Authorization") token: String
    ): Call<KhsMahasiswaResponse>

    @GET("/api/matkul/{id}")
    fun detailMatkulMahasiswa(
        @Header("Authorization") token: String,
        @Path("id")id: Int
    ): Call<DetailMatkulMahasiswaResponse>

    @GET("/api/khs/{id}")
    fun detailKhsMahasiswa(
        @Header("Authorization") token: String,
        @Path("id")id: Int
    ): Call<DetailKhsMahasiswaResponse>

}