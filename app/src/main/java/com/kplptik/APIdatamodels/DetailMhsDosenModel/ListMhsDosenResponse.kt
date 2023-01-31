package com.kplptik.APIdatamodels.DetailMhsDosenModel

import com.google.gson.annotations.SerializedName

data class ListMhsDosenResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class Data(

    @field:SerializedName("nim")
    val nim: String? = null,

    @field:SerializedName("no_hp")
    val noHp: String? = null,

    @field:SerializedName("nama_mahasiswa")
    val namaMahasiswa: String? = null,

    @field:SerializedName("status_mhs")
    val statusMhs: String? = null,

    @field:SerializedName("nama_fak")
    val namaFak: String? = null,

    @field:SerializedName("id_mahasiswa")
    val idMahasiswa: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("nama_jur")
    val namaJur: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null
)
