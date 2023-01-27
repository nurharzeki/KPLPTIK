package com.kplptik.APIdatamodels.ListV

import com.google.gson.annotations.SerializedName

data class DetailMatkulResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class Data(

    @field:SerializedName("reg_mk")
    val regMk: String? = null,

    @field:SerializedName("id_matkul")
    val idMatkul: Int? = null,

    @field:SerializedName("nama_dosen")
    val namaDosen: String? = null,

    @field:SerializedName("nama_mk")
    val namaMk: String? = null,

    @field:SerializedName("jam_kuliah")
    val jamKuliah: String? = null,

    @field:SerializedName("sks")
    val sks: String? = null,

    @field:SerializedName("nama_hari")
    val namaHari: String? = null,

    @field:SerializedName("kode_ruang")
    val kodeRuang: String? = null
)
