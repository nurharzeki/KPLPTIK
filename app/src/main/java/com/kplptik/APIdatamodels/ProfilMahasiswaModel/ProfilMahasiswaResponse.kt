package com.kplptik.APIdatamodels.ProfilMahasiswaModel

import com.google.gson.annotations.SerializedName

data class ProfilMahasiswaResponse(

    @field:SerializedName("datetime")
    val datetime: String? = null,

    @field:SerializedName("profil")
    val profil: Profil? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class Profil(

    @field:SerializedName("nim")
    val nim: String? = null,

    @field:SerializedName("no_hp")
    val noHp: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: Any? = null,

    @field:SerializedName("nama_mahasiswa")
    val namaMahasiswa: String? = null,

    @field:SerializedName("id_fak")
    val idFak: Int? = null,

    @field:SerializedName("status_mhs")
    val statusMhs: String? = null,

    @field:SerializedName("id_jur")
    val idJur: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("jenis_kelamin")
    val jenisKelamin: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null
)
