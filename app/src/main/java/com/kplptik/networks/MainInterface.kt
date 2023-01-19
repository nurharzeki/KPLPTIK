package com.kplptik.networks


import com.kplptik.APIdatamodels.MatkulDiampuModel.MatkulDiampuResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.Call

interface MainInterface {

    @GET("/api/list-matkul/{nip-dosen}")
    fun listmatkuldiampudosen(
        @Header("Authorization") token: String,
        @Path("nip-dosen") nip: String
    ): Call<MatkulDiampuResponse>
}