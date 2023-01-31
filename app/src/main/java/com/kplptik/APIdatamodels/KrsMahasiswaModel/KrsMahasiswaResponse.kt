package com.kplptik.APIdatamodels.KrsMahasiswaModel

import com.google.gson.annotations.SerializedName

data class KrsMahasiswaResponse(

    @field:SerializedName("datetime")
    val datetime: String? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("detail")
    val detail: List<DetailItem?>? = null
)

data class DetailItem(

    @field:SerializedName("reg_mk")
    val regMk: String? = null,

    @field:SerializedName("status_pa")
    val statusPa: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: Any? = null,

    @field:SerializedName("id_mhs")
    val idMhs: Int? = null,

    @field:SerializedName("id_mk")
    val idMk: Int? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("nama_dosen")
    val namaDosen: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: Any? = null,

    @field:SerializedName("nip")
    val nip: String? = null,

    @field:SerializedName("id_fak")
    val idFak: Int? = null,

    @field:SerializedName("nama_mk")
    val namaMk: String? = null,

    @field:SerializedName("jam_kuliah")
    val jamKuliah: String? = null,

    @field:SerializedName("id_jur")
    val idJur: Int? = null,

    @field:SerializedName("semester")
    val semester: String? = null,

    @field:SerializedName("sks")
    val sks: String? = null,

    @field:SerializedName("nama_hari")
    val namaHari: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("jenis_kelamin")
    val jenisKelamin: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("kode_ruang")
    val kodeRuang: String? = null
)
