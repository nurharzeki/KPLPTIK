package com.kplptik.APIdatamodels.ListMahasiswaBimbinganModel

import com.google.gson.annotations.SerializedName

data class ListMahasiswaResponse(

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class DataItem(

    @field:SerializedName("nim")
    val nim: String? = null,

    @field:SerializedName("nama_dosen")
    val namaDosen: String? = null,

    @field:SerializedName("no_hp")
    val noHp: String? = null,

    @field:SerializedName("nama_mahasiswa")
    val namaMahasiswa: String? = null,

    @field:SerializedName("status_mhs")
    val statusMhs: String? = null,

    @field:SerializedName("id_mahasiswa")
    val idMahasiswa: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null
)
