package com.kplptik.APIdatamodels.ProfilDosenModel

import com.google.gson.annotations.SerializedName

data class ProfilDosenResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class Data(

    @field:SerializedName("id_dosen")
    val idDosen: Int? = null,

    @field:SerializedName("nama_dosen")
    val namaDosen: String? = null,

    @field:SerializedName("nip")
    val nip: String? = null,

    @field:SerializedName("status_pa")
    val statusPa: String? = null,

    @field:SerializedName("nama_fak")
    val namaFak: String? = null,

    @field:SerializedName("jenis_kelamin")
    val jenisKelamin: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("nama_jur")
    val namaJur: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null
)
